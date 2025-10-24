package com.zerodha.model;

public class OrderValidator {
    private AccountManager accountManager;

    public OrderValidator(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    public boolean validateOrder(String userId, Order order){
        boolean res = false;
        if(order.getTxnType().equals(TXN_TYPE.BUY)){
            System.out.println("checking if user has enough funds");
            double userBalance = accountManager.getBalance(userId);
            double totalOrderPurchaseCost = order.getQuantity()* order.getPrice();
            if(totalOrderPurchaseCost>userBalance){
                System.out.println("insufficient user fund for buy order.\nOrder validation failed");
            }
            else{
                accountManager.debit(userId, totalOrderPurchaseCost);
                res = true;
            }
        }
        else{
            System.out.println("checking if user has stock to sell");
        }
        return res;
    }
}
