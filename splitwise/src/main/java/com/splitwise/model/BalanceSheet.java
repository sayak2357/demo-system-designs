package com.splitwise.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BalanceSheet {

    private Map<String,Double> balanceMap; // key: user1Id#user2Id, value: amount user1 owes to user2;

    public BalanceSheet() {
        this.balanceMap = new HashMap<>();
    }

    public void addExpenseImproved(int lenderId, int borrowerId, double amount){

        String key = String.valueOf(borrowerId)+"#"+String.valueOf(lenderId);
        this.balanceMap.put(key,balanceMap.getOrDefault(key,0d)+amount);

    }
    public double getDueAmount(int lenderId, int borrowerId){
        String key = String.valueOf(borrowerId)+"#"+String.valueOf(lenderId);
        return this.balanceMap.getOrDefault(key,0d);
    }
    public void setDueAmount(int lenderId,int borrowerId,double amount){
        String key = String.valueOf(borrowerId)+"#"+String.valueOf(lenderId);
        this.balanceMap.put(key,amount);
    }
    public Set<String> getKeys(){
        return this.balanceMap.keySet();
    }
    /*
    public Map<String, Double> getBalanceMap() {
        return balanceMap;
    }
     */
}
