package entities;

import java.util.Random;
public class Card {
    private String cardId;
    private int userId;
    private int pin;
    public Card(String cardId, int userId) {
        this.cardId = cardId;
        this.userId = userId;
        Random random = new Random();
        this.pin = 1000 + random.nextInt(9000);
    }

    public String getCardId() {
        return cardId;
    }

    public int getPin() {
        return pin;
    }

    public int getUserId() {
        return userId;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
