package com.splitwise;

import com.splitwise.model.*;
import com.splitwise.notification.ExpenseNotifier;
import com.splitwise.service.ExpenseService;
import com.splitwise.service.GroupService;
import com.splitwise.service.UserService;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");
        UserService userService = new UserService();
        User sayak = new User("Sayak");
        User akash = new User("Akash");
        userService.addUser(sayak);
        userService.addUser(akash);
        ExpenseNotifier expenseNotifier = new ExpenseNotifier();
        ExpenseService expenseService = new ExpenseService(userService,expenseNotifier);
        Split newSplit = new EqualSplit(sayak.getId(),100, Arrays.asList(akash.getId(),sayak.getId()));
        expenseService.addExpense("outing",newSplit);
        Split newSplit2 = new ExactSplit(akash.getId(), 200,Arrays.asList(sayak.getId()));
        expenseService.addExpense("Dinner",newSplit2);

        Split newSplit3 = new PercentageSplit(sayak.getId(), 200d,Arrays.asList(akash.getId()),30d);
        expenseService.addExpense("party",newSplit3);
        expenseService.showBalancesImproved();



        // Group
        Group testGroup = new Group("TestGroup", List.of(sayak,akash));
        Split testGroupExp1 = new ExactSplit(sayak.getId(), 150,List.of(akash.getId()));
        GroupService groupService = new GroupService(userService);
        groupService.addGroup(testGroup);
        groupService.addExpense("tour", testGroup.getName(), testGroupExp1);
        groupService.showBalance(testGroup.getName());
    }
}