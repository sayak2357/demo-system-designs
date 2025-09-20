package vendingMachine;

import Inventory.Inventory;
import Inventory.Product;
import States.CoinInsertedState;
import States.DispenseState;
import States.NoCoinInsertedState;
import States.State;

// Client will have access to this class only
public class VendingMachine {
    private NoCoinInsertedState noCoinInsertedState;
    private CoinInsertedState coinInsertedState;
    private DispenseState dispenseState;
    private State currVendingMachineState;
    private Inventory inventory;
    private double amount;
    private static final int AISLE_COUNT = 2;
    private ChangeDispenser changeDispenser;

    public ChangeDispenser getChangeDispenser() {
        return changeDispenser;
    }

    public VendingMachine() {
        noCoinInsertedState = new NoCoinInsertedState(this);
        coinInsertedState = new CoinInsertedState(this);
        dispenseState = new DispenseState(this);
        currVendingMachineState = noCoinInsertedState;
        amount = 0.0;
        inventory = new Inventory(AISLE_COUNT);
        changeDispenser = new ChangeDispenser();
    }

    public boolean hasSufficientAmount(double expectedAmount){
        return expectedAmount<=this.amount;
    }

    public NoCoinInsertedState getNoCoinInsertedState(){
        return this.noCoinInsertedState;
    }
    public void setNoCoinInsertedState(NoCoinInsertedState noCoinInsertedState){
        this.noCoinInsertedState = noCoinInsertedState;
    }
    public CoinInsertedState getCoinInsertedState(){
        return this.coinInsertedState;
    }
    public void setCoinInsertedState(CoinInsertedState coinInsertedState){
        this.coinInsertedState = coinInsertedState;
    }

    public DispenseState getDispenseState(){
        return this.dispenseState;
    }

    public void setDispenseState(DispenseState dispenseState){
        this.dispenseState = dispenseState;
    }
    public State getCurrVendingMachineState(){
        return currVendingMachineState;
    }
    public void setCurrVendingMachineState(State currVendingMachineState){
        this.currVendingMachineState = currVendingMachineState;
    }
    public Inventory getInventory(){
        return inventory;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }
    public double getAmount(){
        return this.amount;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public void insertCoin(double amount){
        this.currVendingMachineState.insertCoin(amount);
        System.out.println(amount+" coins inserted");
    }
    public void pressButton(int aisleNumber) throws Exception {
        this.currVendingMachineState.pressButton(aisleNumber);
        this.currVendingMachineState.dispense(aisleNumber);
    }
    public void addProduct(Product product){
        try{
            this.inventory.addProduct(product);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
