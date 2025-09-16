package com.splitwise.service;

import com.splitwise.model.BalanceSheet;

import java.util.Map;
import java.util.Set;

public class BalanceSheetService {
    private BalanceSheet balanceSheet;
   // private Map<String,Double> balanceMap;
    public BalanceSheetService(){
        this.balanceSheet = new BalanceSheet();
     //   this.balanceMap = this.balanceSheet.getBalanceMap();
    }

    public void addExpenseImproved(int lenderId, int borrowerId, double amount){

       // String key = String.valueOf(borrowerId)+"#"+String.valueOf(lenderId);

        this.balanceSheet.addExpenseImproved(lenderId,borrowerId,amount);
        rebalance(lenderId,borrowerId);
    }
    private void rebalance(int lenderId,int borrowerId){
        double borrrowerOwes = this.balanceSheet.getDueAmount(lenderId,borrowerId);
        double lenderOwes = this.balanceSheet.getDueAmount(borrowerId,lenderId);
        if(borrrowerOwes>=lenderOwes){
            Double newBorrowerOweAmount = borrrowerOwes-lenderOwes;
            this.balanceSheet.setDueAmount(borrowerId,lenderId,0d);
            /*
            balanceMap.put(reverseKey,0d);
            balanceMap.put(forwardKey,newBorrowerOweAmount);
             */
            this.balanceSheet.setDueAmount(lenderId,borrowerId,newBorrowerOweAmount);
        }
        else{
            Double newLenderOweAmount = lenderOwes-borrrowerOwes;
            /*
            balanceMap.put(forwardKey,0d);
            balanceMap.put(reverseKey,newLenderOweAmount);
             */
            this.balanceSheet.setDueAmount(lenderId,borrowerId,0d);
            this.balanceSheet.setDueAmount(borrowerId,lenderId,newLenderOweAmount);
        }
    }
    public double fetchBorrowAmount(int borrowerId, int lenderId){

        // String key = String.valueOf(borrowerId)+"#"+String.valueOf(lenderId);

       // this.balanceSheet.addExpenseImproved(lenderId,borrowerId,amount);
        return this.balanceSheet.getDueAmount(lenderId,borrowerId);
    }
    public Set<String> getKeySet(){
       return this.balanceSheet.getKeys();
    }

}
