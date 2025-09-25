//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome to Rate Limiter");

        RateLimiter limiter = new RateLimiter(new SlidingWindowLogRateLimiter(3,10));
        String userId = "testUser";

        for(int i=0;i<5;i++){
            boolean isAllowed = limiter.allowRequest(userId);
            System.out.println("Request "+i+" is allowed? "+isAllowed);
            Thread.sleep(2000);
        }

    }
}