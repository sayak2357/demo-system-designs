package com.foodOrder;

import com.foodOrder.entity.MenuItem;
import com.foodOrder.entity.Order;
import com.foodOrder.entity.OrderStatus;
import com.foodOrder.entity.User;
import com.foodOrder.service.OrderingService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        User user = new User("u1","Sam");
        MenuItem biryani = new MenuItem("m1","biryani",200);
        MenuItem pizza = new MenuItem("m2","pizza",300);

        user.getCart().addItem(biryani);
        user.getCart().addItem(pizza);

        OrderingService orderingService = new OrderingService();
        Order order = orderingService.placeOrder(user);

        System.out.println("Order placed with id: "+order.getId());

        orderingService.updateOrderStatus(order.getId(), OrderStatus.CONFIRMED);
    }
}