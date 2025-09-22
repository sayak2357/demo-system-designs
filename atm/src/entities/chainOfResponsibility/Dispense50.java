package entities.chainOfResponsibility;

public class Dispense50 extends CashDispenser{

    @Override
    public void dispense(int amount){
        if(amount>=50){
            int notes = amount/50;
            int remainder = amount % 50;
            System.out.println("Dispensing " + notes + " x 50 notes");
            if (remainder != 0 && next != null) {
                next.dispense(remainder);
            }
        }
        else if(next!=null){
            next.dispense(amount);
        }
    }
}
