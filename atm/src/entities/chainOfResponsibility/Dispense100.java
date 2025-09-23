package entities.chainOfResponsibility;

public class Dispense100 extends CashDispenser{

    @Override
    public void dispense(int amount){
        if(amount>=100){
            int notes = amount/100;
            int remainder = amount % 100;
            System.out.println("Dispensing " + notes + " x 100 notes");
            if (remainder != 0 && next != null) {
                next.dispense(remainder);
            }
        }
        else if(next!=null){
            next.dispense(amount);
        }
    }
}
