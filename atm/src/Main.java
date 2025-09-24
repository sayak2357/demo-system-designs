import entities.ATM;
import entities.Card;
import entities.User;
import service.BankingService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.


        System.out.println("Hello and welcome to ATM services!");

        // Using banking service
        User sam = new User("sam",1);
        BankingService bs = new BankingService();
        bs.registerUser(sam);
        bs.deposit(sam.getId(), 5000);
        System.out.println("current account balance: "+bs.getBalance(sam.getId()));
        Card samCard = bs.issuseCard(sam);
        System.out.println(samCard.toString());

        // USE ATM
        ATM atm = new ATM(2000,bs);
        atm.insertCard(samCard);
        atm.enterPin(samCard.getPin());
        atm.withdrawCash(1300);
        System.out.println("updated account balance: "+bs.getBalance(sam.getId()));
        System.out.println("Remaining cash in ATM: " + atm.getCash());

    }
}