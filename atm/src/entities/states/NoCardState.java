package entities.states;

import entities.ATM;

public class NoCardState implements ATMState{
    private ATM atm;

    public NoCardState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("card inserted");
        atm.setCurrentState(atm.getHasCardState());
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("No card inserted");
    }

    @Override
    public void withdrawCash(int amount) {
        System.out.println("No card inserted");
    }
}
