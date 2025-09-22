package entities.chainOfResponsibility;

public class Dispense500 extends CashDispenser{

    @Override
    public void dispense(int amount){
        if(amount>=500){
            int notes = amount/500;
            int remainder = amount % 500;
            System.out.println("Dispensing " + notes + " x 500 notes");
            if (remainder != 0 && next != null) {
                next.dispense(remainder);
            }
        }
        else if(next!=null){
            next.dispense(amount);
        }
    }
}
