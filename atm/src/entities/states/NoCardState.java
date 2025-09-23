package entities.states;

import entities.ATM;
import entities.Card;

public class NoCardState implements ATMState{
    private ATM atm;

    public NoCardState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("card inserted");
        atm.setCard(card);
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
