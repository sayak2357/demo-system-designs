package com.splitwise.model;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {
    private Map<Integer, Map<Integer,Double>> balances; // key: user ids who owes

    public BalanceSheet() {
        this.balances = new HashMap<>();
    }
    public void addExpense(int lenderId,int borrowerId,double amount){
        if(!this.balances.containsKey(borrowerId)){
            this.balances.put(borrowerId,new HashMap<>());
        }
        this.balances.get(borrowerId).put(lenderId,this.balances.get(borrowerId).getOrDefault(lenderId,0d)+amount);

    }

    public Map<Integer, Map<Integer, Double>> getBalances() {
        return balances;
    }
}
