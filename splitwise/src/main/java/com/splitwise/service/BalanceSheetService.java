package com.splitwise.service;

import com.splitwise.model.BalanceSheet;

import java.util.Map;

public class BalanceSheetService {
    private BalanceSheet balanceSheet;
    private Map<String,Double> balanceMap;
    public BalanceSheetService(){
        this.balanceSheet = new BalanceSheet();
        this.balanceMap = this.balanceSheet.getBalanceMap();
    }

    public void addExpenseImproved(int lenderId, int borrowerId, double amount){

        String key = String.valueOf(borrowerId)+"#"+String.valueOf(lenderId);
        this.balanceMap.put(key,balanceMap.getOrDefault(key,0d)+amount);
        rebalance(lenderId,borrowerId);
    }
    private void rebalance(int lenderId,int borrowerId){
        String forwardKey = String.valueOf(borrowerId)+"#"+String.valueOf(lenderId);
        String reverseKey = String.valueOf(lenderId)+"#"+String.valueOf(borrowerId);
        double borrrowerOwes = balanceMap.getOrDefault(forwardKey,0d);
        double lenderOwes = balanceMap.getOrDefault(reverseKey,0d);
        if(borrrowerOwes>=lenderOwes){
            Double newBorrowerOweAmount = borrrowerOwes-lenderOwes;
            balanceMap.put(reverseKey,0d);
            balanceMap.put(forwardKey,newBorrowerOweAmount);
        }
        else{
            Double newLenderOweAmount = lenderOwes-borrrowerOwes;
            balanceMap.put(forwardKey,0d);
            balanceMap.put(reverseKey,newLenderOweAmount);
        }
    }

    public Map<String, Double> getBalanceMap() {
        return balanceMap;
    }
}
