package com.accenture.ws;

import com.accenture.ws.entity.Order;
import com.accenture.ws.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


import java.util.List;
import java.util.Optional;

@SpringBootTest
@ContextConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderAndBillingTests {

    @Autowired
    OrderRepository orderRepo;

    @Test
    @org.junit.jupiter.api.Order(1)
    public void addOrderTest() {
        // Declaration Object
        Order order = new Order();
        // Using setter
        order.setOrderName("Espresso");
        order.setPrice(4.5);
        order.setDiscounted(true);
        if (order.getIsDiscounted()) {
            order.setIsDiscountPercentage(5.0);
        } else {
            order.setIsDiscountPercentage(0);
        }
        orderRepo.save(order);

        Assert.assertTrue(order.getOrderName().equalsIgnoreCase("Espresso"));
//        Assertions.assertThat(order.getOrderName().equalsIgnoreCase("Espresso");
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void getOrderListTest() {
        // Get All Data from Database and put to List
        List<Order> orderList = orderRepo.findAll();

        // Check Data, if > 0 will success
        Assertions.assertThat(orderList.size()).isGreaterThan(0);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void updateOrderTest() {
        // Get Data by ID
        Order order = orderRepo.findById(Long.valueOf(1)).get();
        // Using Setter
        order.setOrderName("Cafe Latte");
        order.setPrice(20.25);
        order.setDiscounted(true);
        // Check if discount == true then discountpercentage 5
        if (order.getIsDiscounted()) {
            order.setIsDiscountPercentage(5.0);
        } else {
            order.setIsDiscountPercentage(0);
        }
        // Save Order
        orderRepo.save(order);

        // Update will be success and also check same expected and actual
        Assertions.assertThat(order.getOrderName()).isEqualTo("Cafe Latte");
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void deleteOrderTest() {
        Long id = Long.valueOf(1);
        Order order = orderRepo.findById(Long.valueOf(id)).get();
        orderRepo.delete(order);

        Order orderNull = null;
        Optional<Order> orderDeleted = orderRepo.findById(Long.valueOf(id));

        if (orderDeleted.isPresent()) {
            orderNull = orderDeleted.get();
        }
        Assertions.assertThat(orderNull).isNull();
    }

}