package com.splitwise.service;

import com.splitwise.model.BalanceSheet;
import com.splitwise.model.Group;
import com.splitwise.model.Split;

import java.util.HashMap;
import java.util.Map;

public class GroupService {
    private Map<String,Group> groups;
    private Map<String, ExpenseService> expenseServiceMap;
    private UserService userService;
    public GroupService(UserService userService){
        this.groups = new HashMap<>();
        this.expenseServiceMap = new HashMap<>();
        this.userService = userService;
    }
    public void addGroup(Group group){
        if(!this.groups.containsKey(group.getName())){
            this.groups.put(group.getName(),group);
        }
        if(!this.expenseServiceMap.containsKey(group.getName())){
            this.expenseServiceMap.put(group.getName(),new ExpenseService(this.userService));
        }
    }
    public void addExpense(String groupName, Split split){
        Group curr = this.groups.get(groupName);
        ExpenseService exp = expenseServiceMap.get(groupName);
        exp.addExpense(split);
    }
    public void showBalance(String groupName){
        Group curr = this.groups.get(groupName);
        ExpenseService exp = expenseServiceMap.get(groupName);
        exp.showBalancesImproved();
    }
}
