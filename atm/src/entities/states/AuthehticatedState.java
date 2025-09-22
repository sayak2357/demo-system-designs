package entities.states;

import entities.ATM;

public class AuthehticatedState implements ATMState{
    private ATM atm;

    public AuthehticatedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("Card already authenticated");
    }

    @Override
    public void withdrawCash(int amount) {
        if (atm.getCash() < amount) {
            System.out.println("ATM has insufficient funds. Ejecting card.");
            atm.setCurrentState(atm.getNoCardState());
            return;
        }

        // Delegate to Chain of Responsibility for cash dispensing
        atm.getDispenserChain().dispense(amount);

        atm.setCash(atm.getCash() - amount);
        System.out.println("Cash dispensed: " + amount);

        atm.setCurrentState(atm.getNoCardState());
    }
}
