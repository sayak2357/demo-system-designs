package com.zerodha.model;

public class ExchangeConnector {
    private static ExchangeConnector instance;
    private ExchangeConnector(){

    }

    public static ExchangeConnector getInstance(){
        if(instance==null){
            synchronized (ExchangeConnector.class){
                if(instance==null) {
                    instance = new ExchangeConnector();
                }
            }
        }
        return instance;
    }
    public static void sendOrderToExchange(String userId, Order order){
        System.out.println("Order: "+order.toString()+" has been sent to exchange. Congratulations!");

    }
}
