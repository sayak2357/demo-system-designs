package com.splitwise.service;

import com.splitwise.model.*;
import com.splitwise.notification.ExpenseNotifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExpenseService {
    //private BalanceSheet balanceSheet;
    private UserService userService;
    private BalanceSheetService balanceSheetService;
    private ExpenseNotifier expenseNotifier;
    public ExpenseService(UserService userService, ExpenseNotifier expenseNotifier){
        //this.balanceSheet = new BalanceSheet();
        this.userService = userService;
        this.balanceSheetService = new BalanceSheetService();
        this.expenseNotifier = expenseNotifier;
    }
    public void addExpense(String description,Split split){
        double amount = split.getAmount();
        List<Integer> beneficiaryIds = split.getBeneficiaryIds();
        int lenderId = split.getPaidUserId();
        SplitType splitType = split.getSplitType();
        int size;
        double share,percentage;
        User borrower = null;
        String msg = "New Expense Added: " + description + " - Amount: $" + amount;
        List<User> observers = new ArrayList<>();
        // Register observers dynamically for this expense
        switch(splitType){
            case EQUAL: size = beneficiaryIds.size();
                        share = amount/size;
                        for(int borrowerId:beneficiaryIds){
                            if(borrowerId!=lenderId){
                                this.balanceSheetService.addExpenseImproved(lenderId,borrowerId,share);
                                borrower = userService.getUser(borrowerId);
                                expenseNotifier.registerObserver(borrower);
                                observers.add(borrower);
                            }
                        }
                        break;
            case EXACT: this.balanceSheetService.addExpenseImproved(lenderId,beneficiaryIds.get(0),amount);
                borrower = userService.getUser(beneficiaryIds.get(0));
                expenseNotifier.registerObserver(borrower);
                observers.add(borrower);
                break;
            case PERCENTAGE: percentage = ((PercentageSplit) split).getPercentage();
                            size = beneficiaryIds.size();
                            share = amount*percentage/100;
                            for(int borrowerId:beneficiaryIds){
                                if(borrowerId!=lenderId){
                                    this.balanceSheetService.addExpenseImproved(lenderId,borrowerId,share);
                                    borrower = userService.getUser(borrowerId);
                                    observers.add(borrower);
                                    expenseNotifier.registerObserver(borrower);
                                }
                            }
                            break;

        }
        // Notify all users
        expenseNotifier.notifyObservers(msg);

        // Clean up observers for next operation
        for (User user : observers) {
            expenseNotifier.removeObserver(user);
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
