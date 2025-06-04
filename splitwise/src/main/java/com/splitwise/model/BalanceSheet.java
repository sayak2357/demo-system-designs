package com.splitwise.model;

import java.util.HashMap;
import java.util.Map;

public class BalanceSheet {

    private Map<String,Double> balanceMap; // key: user1Id#user2Id, value: amount user1 owes to user2;

    public BalanceSheet() {
        this.balanceMap = new HashMap<>();
    }


    public Map<String, Double> getBalanceMap() {
        return balanceMap;
    }
}
