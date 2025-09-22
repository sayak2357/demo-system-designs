package entities.chainOfResponsibility;

public class Dispense10 extends CashDispenser{
    @Override
    public void dispense(int amount){
        if(amount>=10){
            int notes = amount/10;
            int remainder = amount % 10;
            System.out.println("Dispensing " + notes + " x 10 notes");
            if (remainder != 0 && next != null) {
                next.dispense(remainder);
            }
        }
        else if(next!=null){
            next.dispense(amount);
        }
    }
}
