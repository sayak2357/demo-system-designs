import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiterStrategy {

    private final long refillRate; // tokens per second
    private final int capacity;
    private final Map<String, UserBucket> userBuckets;

    public TokenBucketRateLimiter(long refillRate, int capacity) {
        this.refillRate = refillRate;
        this.capacity = capacity;
        this.userBuckets = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        long currTime = System.currentTimeMillis();

        // initialize user bucket if absent
        userBuckets.putIfAbsent(userId, new UserBucket(capacity, currTime));
        UserBucket bucket = userBuckets.get(userId);

        synchronized (bucket) { // synchronize per-user bucket
            // refill tokens based on time elapsed
            long elapsedSeconds = (currTime - bucket.lastRefillTimestamp) / 1000;
            if (elapsedSeconds > 0) {
                int refillTokens = (int) Math.min(bucket.tokens + elapsedSeconds * refillRate, capacity);
                bucket.tokens = refillTokens;
                bucket.lastRefillTimestamp = currTime;
            }

            if (bucket.tokens > 0) {
                bucket.tokens -= 1; // consume a token
                return true;
            } else {
                return false; // rate limit exceeded
            }
        }
    }

    // Inner class to hold per-user bucket state
    private static class UserBucket {
        int tokens;
        long lastRefillTimestamp;

        UserBucket(int tokens, long lastRefillTimestamp) {
            this.tokens = tokens;
            this.lastRefillTimestamp = lastRefillTimestamp;
        }
    }
}
