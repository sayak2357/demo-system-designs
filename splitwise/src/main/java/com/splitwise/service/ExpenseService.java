package com.splitwise.service;

import com.splitwise.model.BalanceSheet;
import com.splitwise.model.PercentageSplit;
import com.splitwise.model.Split;
import com.splitwise.model.SplitType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExpenseService {
    //private BalanceSheet balanceSheet;
    private UserService userService;
    private BalanceSheetService balanceSheetService;
    public ExpenseService(UserService userService){
        //this.balanceSheet = new BalanceSheet();
        this.userService = userService;
        this.balanceSheetService = new BalanceSheetService();
    }
    public void addExpense(Split split){
        double amount = split.getAmount();
        List<Integer> beneficiaryIds = split.getBeneficiaryIds();
        int lenderId = split.getPaidUserId();
        SplitType splitType = split.getSplitType();
        int size;
        double share,percentage;
        switch(splitType){
            case EQUAL: size = beneficiaryIds.size();
                        share = amount/size;
                        for(int borrowerId:beneficiaryIds){
                            if(borrowerId!=lenderId){
                                this.balanceSheetService.addExpenseImproved(lenderId,borrowerId,share);
                            }
                        }
                        break;
            case EXACT: this.balanceSheetService.addExpenseImproved(lenderId,beneficiaryIds.get(0),amount);break;
            case PERCENTGE: percentage = ((PercentageSplit) split).getPercentage();
                            size = beneficiaryIds.size();
                            share = amount*percentage/100;
                            for(int borrowerId:beneficiaryIds){
                                if(borrowerId!=lenderId){
                                    this.balanceSheetService.addExpenseImproved(lenderId,borrowerId,share);
                                }
                            }
                            break;

        }
    }

    public void showBalancesImproved(){

        Set<String> borrowerHashLenderKeys = this.balanceSheetService.getKeySet();

        for(String borrowerHashLender:borrowerHashLenderKeys){
            String key = borrowerHashLender;
            int borrowerId = Integer.parseInt(key.split("#")[0]);
            int lenderId = Integer.parseInt(key.split("#")[1]);
            double amount = this.balanceSheetService.fetchBorrowAmount(borrowerId,lenderId);
            String borrowerName = userService.getUser(borrowerId).getName();
            String lenderName = userService.getUser(lenderId).getName();
            if(amount>0d){
                System.out.println(borrowerName+" owes an amount of "+amount+" to "+lenderName);
            }
        }

    }
}
