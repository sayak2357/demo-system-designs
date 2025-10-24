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
                accountManager.addStockToPortfolio(userId,order);
                res = true;
            }
        }
        else{
            Stock toSell = order.getStock();
            int quantity = order.getQuantity();
            System.out.println("checking if user has stock to sell");
            int portfolioQuantity = accountManager.getStockQuantity(userId,toSell);
            if(quantity>portfolioQuantity){
                System.out.println("not enough stocks in portfolio to sell");
            }
            else{
                res = true;
                accountManager.removeStockFromPortfolio(userId,order);
            }

        }
        return res;
    }
}
