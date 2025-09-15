package States;

/*
* 3 different states
* NoCoinInsertedState --> CoinInsertedState --> DispenseState --> NoCoinInsertedState
* */

public interface State {
    public void insertCoin(double number);
    public void pressButton(int aisleNumber);
    public void dispense(int aisleNumber);
}
