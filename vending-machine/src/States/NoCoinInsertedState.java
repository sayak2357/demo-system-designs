package States;

import vendingMachine.VendingMachine;

public class NoCoinInsertedState implements State{
    private VendingMachine vendingMachine;

    public NoCoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public synchronized void insertCoin(double amount) {
        vendingMachine.setAmount(amount);
        vendingMachine.getChangeDispenser().addCoin(amount);
        vendingMachine.setCurrVendingMachineState(vendingMachine.getCoinInsertedState());

    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalStateException("No coin inserted");
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalStateException("No coin inserted");
    }
}
