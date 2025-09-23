package entities;

import entities.chainOfResponsibility.*;
import entities.states.ATMState;
import entities.states.AuthehticatedState;
import entities.states.HasCardState;
import entities.states.NoCardState;
import service.BankingService;

public class ATM {
    private ATMState noCardState;
    private ATMState hasCardState;
    private ATMState authenticatedState;
    private ATMState currentState;

    private int cash;
    private CashDispenser dispenserChain;
    private BankingService bankingService;
    private Card card;

    public ATM(int initialCAsh, BankingService bankingService) {
        noCardState = new NoCardState(this);
        hasCardState = new HasCardState(this);
        authenticatedState = new AuthehticatedState(this);
        currentState = noCardState;
        this.cash = initialCAsh;
        this.card = null;
        this.bankingService = bankingService;
        // Setup chain of responsibility
        dispenserChain = new Dispense1000();
        CashDispenser dispense500 = new Dispense500();

        CashDispenser dispense100 = new Dispense100();
        CashDispenser dispense50 = new Dispense50();
        CashDispenser dispense10 = new Dispense10();
        dispenserChain.setNext(dispense500);
        dispense500.setNext(dispense100);
        dispense100.setNext(dispense50);
        dispense50.setNext(dispense10);
    }

    // State delegation methods
    public void insertCard(Card card) { currentState.insertCard(card); }
    public void enterPin(int pin) { currentState.enterPin(pin); }
    public void withdrawCash(int amount) { currentState.withdrawCash(amount); }
    public int getCash() {
        return cash;
    }
    public void setCash(int cash) { this.cash = cash; }

    public ATMState getCurrentState() {
        return currentState;
    }

    public ATMState getAuthenticatedState() {
        return authenticatedState;
    }

    public ATMState getHasCardState() {
        return hasCardState;
    }

    public ATMState getNoCardState() {
        return noCardState;
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
    }

    public CashDispenser getDispenserChain() {
        return dispenserChain;
    }

    public void setCard(Card card){ this.card = card;}

    public BankingService getBankingService() {
        return bankingService;
    }

    public Card getCard() {
        return card;
    }
}
