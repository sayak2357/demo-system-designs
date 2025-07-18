package com.foodOrder.service;

import com.foodOrder.entity.MenuItem;
import com.foodOrder.entity.Order;
import com.foodOrder.entity.OrderStatus;
import com.foodOrder.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderingService {

    private List<Order> orders = new ArrayList<>();

    public Order placeOrder(User user){
        List<MenuItem> items = user.getCart().getItems();
        if(items.isEmpty())
            throw new RuntimeException("Cart is empty");
        Order order = new Order(user,items);
        orders.add(order);
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
