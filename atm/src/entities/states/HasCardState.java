package entities.states;

import entities.ATM;

public class HasCardState implements ATMState{
    private ATM atm;

    public HasCardState(ATM atm) {
        this.atm = atm;
    }

    @Override
    public void insertCard() {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(int pin) {
        System.out.println("verify pin");
        if(pin==1234){
            System.out.println("pin is correct");
            atm.setCurrentState(atm.getAuthenticatedState());
        }
        else{
            System.out.println("incorrect pin. Ejecting card.");
            atm.setCurrentState(atm.getNoCardState());
        }
    }

    @Override
    public void withdrawCash(int amount) {
        System.out.println("Enter pin first");
    }
}
