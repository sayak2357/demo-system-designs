import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter implements RateLimiterStrategy {

    private final int maxRequests;
    private final long windowMillis;
    private final Map<String, UserWindow> userWindows;

    public FixedWindowRateLimiter(int maxRequests, long windowSeconds) {
        this.maxRequests = maxRequests;
        this.windowMillis = windowSeconds * 1000;
        this.userWindows = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();

        UserWindow window = userWindows.computeIfAbsent(
                userId,
                k -> new UserWindow(now)
        );

        synchronized (window) {
            long currentWindowStart = window.windowStart;

            // Check if current request falls in same window
            if (now - currentWindowStart < windowMillis) {
                if (window.requestCount < maxRequests) {
                    window.requestCount++;
                    return true;
                } else {
                    return false; // limit exceeded
                }
            } else {
                // New window
                window.windowStart = now;
                window.requestCount = 1;
                return true;
            }
        }
    }

    private static class UserWindow {
        long windowStart;
        int requestCount;

        UserWindow(long windowStart) {
            this.windowStart = windowStart;
            this.requestCount = 0;
        }
    }
}
/*Memory usage (O(1) instead of O(N))*/