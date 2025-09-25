public class RateLimiter {

    private final RateLimiterStrategy strategy;

    public RateLimiter(RateLimiterStrategy strategy) {
        this.strategy = strategy;
    }
    public boolean allowRequest(String userId){
        return strategy.allowRequest(userId);
    }
}
