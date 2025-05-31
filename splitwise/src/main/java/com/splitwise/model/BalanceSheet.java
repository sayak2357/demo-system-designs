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
        double reverseOwe = 0d;
        reverseOwe = balances.containsKey(lenderId) ? balances.get(lenderId).containsKey(borrowerId)? balances.get(lenderId).get(borrowerId):0d:0d;
        double netForwardOwe = Math.max(0,amount-reverseOwe);
        reverseOwe = Math.max(0,reverseOwe-amount);
        this.balances.get(borrowerId).put(lenderId,this.balances.get(borrowerId).getOrDefault(lenderId,0d)+netForwardOwe);
        if(!this.balances.containsKey(lenderId)){
            this.balances.put(lenderId,new HashMap<>());
        }
        this.balances.get(lenderId).put(borrowerId,reverseOwe);
    }

    public Map<Integer, Map<Integer, Double>> getBalances() {
        return balances;
    }
}
