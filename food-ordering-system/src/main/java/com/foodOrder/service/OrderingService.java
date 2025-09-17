package com.foodOrder.service;

import com.foodOrder.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderingService {

    private List<Order> orders = new ArrayList<>();

    public Order placeOrder(User user,PaymentMode mode){
        List<MenuItem> items = user.getCart().getItems();
        if(items.isEmpty())
            throw new RuntimeException("Cart is empty");

        Order order = new Order(user, items);
        orders.add(order);

        double totalAmount = items.stream().mapToDouble(MenuItem::getPrice).sum();

        PaymentService paymentService = PaymentServiceFactory.getPaymentService(mode);
        Payment payment = paymentService.processPayment(order.getId(), totalAmount);

        switch (payment.getStatus()) {
            case SUCCESS:
                order.updateStatus(OrderStatus.CONFIRMED);
                break;
            case PENDING:
                order.updateStatus(OrderStatus.NEW); // could introduce PAYMENT_PENDING
                break;
            case FAILED:
                order.updateStatus(OrderStatus.NEW); // or PAYMENT_FAILED
                break;
        }

        user.getCart().clearCart();
        return order;

    }

    public void updateOrderStatus(String orderId, OrderStatus status){
        for(Order order:orders){
            if(order.getId().equals(orderId)){
                order.updateStatus(status);
                break;
            }
        }
    }

    public List<Order> getOrderByUser(User user){
        return orders.stream().filter(o -> o.getUser().getId().equals(user.getId())).collect(Collectors.toList());
    }
}
