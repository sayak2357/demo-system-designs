import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimiter implements RateLimiterStrategy{
    private final int maxRequests;
    private final long windowMillis;
    private final Map<String, Deque<Long>> userRequestMap;

    public SlidingWindowLogRateLimiter(int maxRequests, long windowSeconds) {
        this.maxRequests = maxRequests;
        this.windowMillis = windowSeconds*1000;
        this.userRequestMap = new ConcurrentHashMap<>();
    }
    public boolean allowRequest(String userId){
        long now = System.currentTimeMillis();
        Deque<Long> timestamps =
                userRequestMap.computeIfAbsent(userId, k -> new LinkedList<>());

        synchronized (timestamps) {
            long limit = now - this.windowMillis;
            while (!timestamps.isEmpty() && timestamps.peekFirst() < limit) {
                timestamps.pollFirst();
            }
            if (timestamps.size() < this.maxRequests) {
                timestamps.add(now);
                return true;
            } else
                return false; // rate limit exceeded
        }
    }
}
// In production, idle users should be evicted periodically