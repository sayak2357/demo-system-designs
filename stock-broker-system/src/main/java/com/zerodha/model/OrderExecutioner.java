package com.zerodha.model;

public class OrderExecutioner {
    private ExchangeConnector connector;
    public void placeOrder(String userId, Order order){
        connector = ExchangeConnector.getInstance();
        connector.sendOrderToExchange(userId,order);
    }
}
