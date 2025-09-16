package com.splitwise.service;

import com.splitwise.model.BalanceSheet;
import com.splitwise.model.Group;
import com.splitwise.model.Split;
import com.splitwise.notification.ExpenseNotifier;

import java.util.HashMap;
import java.util.Map;

public class GroupService {
    private Map<String,Group> groups;
    private Map<String, ExpenseService> expenseServiceMap;
    private UserService userService;
    private ExpenseNotifier expenseNotifier;
    public GroupService(UserService userService){
        this.groups = new HashMap<>();
        this.expenseServiceMap = new HashMap<>();
        this.userService = userService;
        this.expenseNotifier = new ExpenseNotifier();
    }
    public void addGroup(Group group){
        if(!this.groups.containsKey(group.getName())){
            this.groups.put(group.getName(),group);
        }
        if(!this.expenseServiceMap.containsKey(group.getName())){
            this.expenseServiceMap.put(group.getName(),new ExpenseService(this.userService,this.expenseNotifier));
        }
    }
    public void addExpense(String occasion,String groupName, Split split){
        Group curr = this.groups.get(groupName);
        ExpenseService exp = expenseServiceMap.get(groupName);
        exp.addExpense(occasion,split);
    }
    public void showBalance(String groupName){
        Group curr = this.groups.get(groupName);
        ExpenseService exp = expenseServiceMap.get(groupName);
        exp.showBalancesImproved();
    }
}
