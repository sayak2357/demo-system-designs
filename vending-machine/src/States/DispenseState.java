package States;

import Inventory.Inventory;
import Inventory.Product;
import vendingMachine.VendingMachine;

import java.util.List;

public class DispenseState implements State{
    private final VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin(double amount) {
        throw new IllegalStateException("Product getting dispensed");
    }

    @Override
    public void pressButton(int aisleNumber) {
        throw new IllegalStateException("Product getting dispensed");
    }

    @Override
    public synchronized void dispense(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);
        double change = vendingMachine.getAmount() - product.getPrice();

        vendingMachine.setCurrVendingMachineState(vendingMachine.getNoCoinInsertedState());

        List<Double> coins = null;
        try {
            coins = vendingMachine.getChangeDispenser().getChange(change);
            inventory.deductProductCount(aisleNumber);
            System.out.println("Product with id:"+product.getId()+" is dispensed.");
            System.out.println("You're getting an amount of rupees "+change+" as change"+" with denominations: "+coins);
        }catch (Exception e){
            double refundedAmount = vendingMachine.getAmount();
            System.out.println("can't dispense amount. No denominations on stock. Your total money: "+refundedAmount+"₹  is refunded.");
            vendingMachine.setAmount(0);
            e.printStackTrace();
        }
        vendingMachine.setAmount(0);
    }
}
