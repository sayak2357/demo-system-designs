package States;

import Inventory.Inventory;
import Inventory.Product;
import vendingMachine.VendingMachine;

public class CoinInsertedState implements State{
    private VendingMachine vendingMachine;

    public CoinInsertedState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }
    @Override
    public void insertCoin(double amount) {
        vendingMachine.setAmount(vendingMachine.getAmount()+amount);
    }

    @Override
    public void pressButton(int aisleNumber) {
        Inventory inventory = vendingMachine.getInventory();
        Product product = inventory.getProductAt(aisleNumber);

        if(!vendingMachine.hasSufficientAmount(product.getPrice())){
            throw new IllegalStateException("Insufficient fund to buy this product");
        }
        if(!inventory.checkIfProductAvailable(product.getId())){
            throw new IllegalStateException("Product not available");
        }

        vendingMachine.setCurrVendingMachineState(vendingMachine.getDispenseState());
    }

    @Override
    public void dispense(int aisleNumber) {
        throw new IllegalStateException("Choose a product first");
    }
}
