package com.foodOrder;

import com.foodOrder.entity.*;
import com.foodOrder.service.OrderingService;
import com.foodOrder.service.RatingService;

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
        Order order = orderingService.placeOrder(user, PaymentMode.CASH_ON_DELIVERY);

        System.out.println("Order placed with id: "+order.getId());

        orderingService.updateOrderStatus(order.getId(), OrderStatus.CONFIRMED);


        //Rating service demo
        RatingService ratingService = new RatingService();

        ratingService.rateMenuItem(biryani,5);
        ratingService.rateMenuItem(biryani,4);
        System.out.println("Biryani average rating: "+ratingService.getRatingCount(biryani));
    }
}