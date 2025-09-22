import entities.ATM;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcom to ATM servicee!");
        ATM atm = new ATM(2000);
        atm.insertCard();
        atm.enterPin(1234);
        atm.withdrawCash(1300);

        System.out.println("Remaining cash in ATM: " + atm.getCash());
    }
}