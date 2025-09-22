package entities.chainOfResponsibility;

public abstract class CashDispenser {
    protected CashDispenser next;

    public void setNext(CashDispenser next) {
        this.next = next;
    }
    public void dispense(int amount){
        if(next!=null)
            next.dispense(amount);
    }
}
