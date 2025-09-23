package entities.states;

import entities.Card;

// States of ATM
public interface ATMState {
    void insertCard(Card card);
    void enterPin(int pin);
    void withdrawCash(int amount);
}
