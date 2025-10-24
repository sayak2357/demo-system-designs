package com.zerodha.model;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private Map<String, Double> balanceMap;  //userId -- balance
    private Map<String,Map<String,UserStock>> stockMap;  // userId -- Map<Stockname,Stock-quantity>

    public AccountManager() {
        this.balanceMap = new HashMap<>();
        this.stockMap = new HashMap<>();
    }

    public double getBalance(String userId){
        return this.balanceMap.getOrDefault(userId,-1d);
    }
    public void openAccount(String userId){
        this.balanceMap.put(userId,0d);
        this.stockMap.put(userId, new HashMap<>());

    }
    public synchronized void addMoney(String userId, double amount){
        if(this.balanceMap.containsKey(userId)){
            this.balanceMap.put(userId,this.balanceMap.get(userId)+amount);
        }
    }
    public synchronized void debit(String userId, double amount){
        if(this.balanceMap.containsKey(userId)){
            this.balanceMap.put(userId,this.balanceMap.get(userId)-amount);
        }
    }
   public synchronized void addStock(String userId,Order order){

   }
    private static class UserStock{
        private Stock stock;
        private int quantity;

        public UserStock(Stock stock, int quantity) {
            this.stock = stock;
            this.quantity = quantity;
        }

        public Stock getStock() {
            return stock;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
