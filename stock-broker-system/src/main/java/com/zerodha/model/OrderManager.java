package com.zerodha.model;

public class OrderManager {
    private OrderValidator orderValidator;
    private OrderExecutioner orderExecutioner;
    private AccountManager accountManager;
    public OrderManager(AccountManager accountManager) {
        this.accountManager = accountManager;
        this.orderValidator = new OrderValidator(accountManager);
        this.orderExecutioner = new OrderExecutioner();
    }

    public void placeOrder(String userId, Order order){
        if(orderValidator.validateOrder(userId,order)){
            System.out.println("order validation successful");
            orderExecutioner.placeOrder(userId,order);
        }

    }
}
