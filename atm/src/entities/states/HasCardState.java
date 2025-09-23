package entities.states;

import entities.ATM;
import entities.Card;

public class HasCardState implements ATMState{
    private ATM atm;

    public HasCardState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard(Card card) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("verify pin");
        Card card = atm.getCard();

        if(atm.getBankingService().authCard(card,pin)){
            System.out.println("pin is correct");
            atm.setCurrentState(atm.getAuthenticatedState());
        }
        else{
            System.out.println("incorrect pin. Ejecting card.");
            atm.setCurrentState(atm.getNoCardState());
            atm.setCard(null);
        }
    }

    @Override
    public void withdrawCash(int amount) {
        System.out.println("Enter pin first");
    }
}
