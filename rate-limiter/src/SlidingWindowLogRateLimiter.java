import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogRateLimiter implements RateLimiterStrategy{
    private int maxRequests;
    private long windowMillis;
    private Map<String, Deque<Long>> userRequestMap;

    public SlidingWindowLogRateLimiter(int maxRequests, long windowSeconds) {
        this.maxRequests = maxRequests;
        this.windowMillis = windowSeconds*1000;
        this.userRequestMap = new ConcurrentHashMap<>();
    }
    public boolean allowRequest(String userId){
        long now = System.currentTimeMillis();
        if(!userRequestMap.containsKey(userId)){
            userRequestMap.put(userId,new LinkedList<>());
        }
        Deque<Long> timestamps = userRequestMap.get(userId);

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
