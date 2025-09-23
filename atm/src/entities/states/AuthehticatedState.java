package entities.states;

import entities.ATM;
import entities.Card;

public class AuthehticatedState implements ATMState{
    private ATM atm;

    public AuthehticatedState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(Card card) {
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
        // check if amount is present in user's bank account
        Card card = atm.getCard();
        double userBalance = atm.getBankingService().getBalanceFromCard(card);
        if(amount>userBalance){
            System.out.println("Balance in account: "+userBalance+"\n is not sufficient.");
            return;
        }

        // deduct amount from user's bank account
        atm.getBankingService().debitFromCard(card,amount);
        // Delegate to Chain of Responsibility for cash dispensing
        atm.getDispenserChain().dispense(amount);

        atm.setCash(atm.getCash() - amount);
        System.out.println("Cash dispensed: " + amount);

        atm.setCurrentState(atm.getNoCardState());
    }
}
