package entities.chainOfResponsibility;

public class Dispense1000 extends CashDispenser{

    @Override
    public void dispense(int amount){
        if(amount>=1000){
            int notes = amount/1000;
            int remainder = amount % 1000;
            System.out.println("Dispensing " + notes + " x 1000 notes");
            if (remainder != 0 && next != null) {
                next.dispense(remainder);
            }
        }
        else if(next!=null){
            next.dispense(amount);
        }
    }
}
