package com.splitwise.service;

import com.splitwise.model.BalanceSheet;
import com.splitwise.model.Split;
import com.splitwise.model.SplitType;

import java.util.List;
import java.util.Map;

public class ExpenseService {
    private BalanceSheet balanceSheet;
    private UserService userService;
    public ExpenseService(UserService userService){
        this.balanceSheet = new BalanceSheet();
        this.userService = userService;
    }
    public void addExpense(Split split){
        double amoutn = split.getAmount();
        List<Integer> beneficiaryIds = split.getBeneficiaryIds();
        int lenderId = split.getPaidUserId();
        SplitType splitType = split.getSplitType();
        switch(splitType){
            case EQUAL: int size = beneficiaryIds.size();
                        double share = amoutn/size;
                        for(int borrowerId:beneficiaryIds){
                            if(borrowerId!=lenderId){
                                this.balanceSheet.addExpense(lenderId,borrowerId,share);
                            }
                        }
                        break;
        }
    }
    public void showBalances(){
        Map<Integer, Map<Integer,Double>> balances = this.balanceSheet.getBalances();
        for(Integer borrowerId:balances.keySet()){
            for(int lenderId:balances.get(borrowerId).keySet()){
                double amount = balances.get(borrowerId).get(lenderId);
                String borrowerName = userService.getUser(borrowerId).getName();
                String lenderName = userService.getUser(lenderId).getName();
                System.out.println(borrowerName+" owes an amount of "+amount+" to "+lenderName);
            }
        }
    }
}
