package com.splitwise;

import com.splitwise.model.Split;
import com.splitwise.model.SplitType;
import com.splitwise.model.User;
import com.splitwise.service.ExpenseService;
import com.splitwise.service.UserService;

import java.util.Arrays;

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
        ExpenseService expenseService = new ExpenseService(userService);
        Split newSplit = new Split(sayak.getId(),100, Arrays.asList(akash.getId(),sayak.getId()), SplitType.EQUAL);
        expenseService.addExpense(newSplit);
        Split newSplit2 = new Split(akash.getId(), 200,Arrays.asList(sayak.getId()),SplitType.EXACT);
        expenseService.addExpense(newSplit2);
        expenseService.showBalances();
    }
}