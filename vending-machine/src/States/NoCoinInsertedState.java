package States;

public class NoCoinInsertedState implements State{

    VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double number) {
        vendingMachine.setAmount(number);
        vendingMachine.setCurrentVendingMachineState(vendingMachine.getConInsertedState());
    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalStateException("No coin is inserted");
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalStateException("No coin is inserted");
    }
}
