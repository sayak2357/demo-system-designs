public class ToenBucketRateLimiter implements RateLimiterStrategy{

    @Override
    public boolean allowRequest(String userId) {
        // **  token bucket **/
        return false;
    }
}
