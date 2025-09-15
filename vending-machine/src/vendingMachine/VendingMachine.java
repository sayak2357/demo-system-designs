package vendingMachine;

import States.CoinInsertedState;
import States.DispenseState;
import States.NoCoinInsertedState;
import States.State;

public class VendingMachine {
    private NoCoinInsertedState noCoinInsertedState;
    private CoinInsertedState coinInsertedState;
    private DispenseState dispenseState;
    private State currentVendingMachineState;
    private Inventroy inventroy;
    private double amount;
    private static final int AISLE_COUNT = 2;
    
}
