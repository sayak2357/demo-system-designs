package com.zerodha;

import com.zerodha.model.*;

public class Main {
    public static void main(String[] args) {


        System.out.println("Hello to Zeroda!");
        User user1 = new User("u1","Sam");
        AccountManager accountManager = new AccountManager();
        accountManager.openAccount(user1.getUserId());
        accountManager.addMoney(user1.getUserId(),50000d);
        Stock stock = new Stock("Gail",1000, EXCH.NSE);
        Order order = new Order(TXN_TYPE.BUY,ORDER_TYPE.LIMIT,500d,20,stock,EXCH.NSE);

        OrderManager orderManager = new OrderManager(accountManager);
        orderManager.placeOrder(user1.getUserId(),order);
    }
}