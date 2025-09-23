package service;


import entities.Card;
import entities.User;

import java.util.*;

public class BankingService {
    private Map<Integer, Double> userIdBalanceMap;
    private Map<Integer, User> userMap;
    private Map<Integer, List<Card>> userIdCardMap;

    public BankingService() {
        userMap = new HashMap<>();
        userIdBalanceMap = new HashMap<>();
        userIdCardMap = new HashMap<>();
    }

    public void deposit(int userId, double amount){
        userIdBalanceMap.put(userId,userIdBalanceMap.getOrDefault(userId,0d)+amount);
    }
    public double getBalance(int userId) {
        if(!userIdBalanceMap.containsKey(userId)) {
            System.out.println("Invalid userId");
            return 0d;
        }
        return userIdBalanceMap.get(userId);
    }
    public double getBalanceFromCard(Card card) {
        int userId = card.getUserId();
        return getBalance(userId);
    }
    public boolean debit(int userId,double amount)  {
        if(userIdBalanceMap.containsKey(userId) && userIdBalanceMap.get(userId)>=amount){
            userIdBalanceMap.put(userId,userIdBalanceMap.get(userId)-amount);
            return true;
        }
        else if(!userIdBalanceMap.containsKey(userId)){
            System.out.println("Invalid userId");
            return false;
        }

        System.out.println("Insufficient funds");
        return false;

    }
    public boolean debitFromCard(Card card, double amount)  {
        int userId = card.getUserId();
        return debit(userId,amount);
    }
    public void registerUser(User user){
        userMap.put(user.getId(),user);
        userIdBalanceMap.put(user.getId(),0d);
    }
    public Card issuseCard(User user){
        Card card = new Card(UUID.randomUUID().toString(),user.getId());
        if(userIdCardMap.containsKey(user.getId())){
            userIdCardMap.get(user.getId()).add(card);
        }
        else{
            List<Card> newList = new ArrayList<>(List.of(card));
            userIdCardMap.put(user.getId(),newList);
        }
        return card;
    }
    public boolean authCard(Card card, int pin){
        return card.getPin()==pin;
    }
}
