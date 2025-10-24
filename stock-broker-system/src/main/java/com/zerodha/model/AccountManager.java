package com.zerodha.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountManager {
    private Map<String, Double> balanceMap;  //userId -- balance
    private Map<String, Map<String,Integer>> stockMap;  // userId -- Map<Stockname,quantity>

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
   public synchronized void addStockToPortfolio(String userId,Order order){
        Stock stock = order.getStock();
        int quantity = order.getQuantity();
        if(stockMap.get(userId).containsKey(stock.getName())){
            int oldQuantity = stockMap.get(userId).get(stock.getName());
            stockMap.get(userId).put(stock.getName(),oldQuantity+quantity);
        }
        else{
            stockMap.get(userId).put(stock.getName(),quantity);
        }
   }

    public synchronized void removeStockFromPortfolio(String userId,Order order){
        if(!order.getTxnType().equals(TXN_TYPE.SELL)){
            System.out.println("invalid operation");
            return;
        }
        Stock stock = order.getStock();
        int quantity = order.getQuantity();
        if(stockMap.get(userId).containsKey(stock.getName())){
            int oldQuantity = stockMap.get(userId).get(stock.getName());
            if(oldQuantity<quantity){
                System.out.println("not enough stocks to sell");
                return;
            }
            stockMap.get(userId).put(stock.getName(),oldQuantity-quantity);
        }
    }

    public synchronized int getStockQuantity(String userId, Stock stock){
        return stockMap.get(userId).getOrDefault(stock.getName(),0);
    }

}
