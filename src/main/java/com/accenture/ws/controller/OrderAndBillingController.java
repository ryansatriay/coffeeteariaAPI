package com.accenture.ws.controller;

import com.accenture.ws.entity.CafeClerk;
import com.accenture.ws.entity.Order;
import com.accenture.ws.impl.DiscountedBill;
import com.accenture.ws.impl.RegularBill;
import com.accenture.ws.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
public class OrderAndBillingController {

    private CafeClerk clerk;

    @Autowired
    OrderRepository orderRepo;

    public OrderAndBillingController() {
    }

    public OrderAndBillingController(CafeClerk clerk) {
        this.clerk = clerk;
    }

    @GetMapping("/orders")
    public List<Order> getOrderList() {
        return orderRepo.findAll();
    }

    @PostMapping("/add")
    public void addOrder (@RequestBody Order order) {
        order.setOrderName(order.getOrderName());
        order.setPrice(order.getPrice());
        order.setDiscounted(order.getIsDiscounted());
        if (order.getIsDiscounted()) {
            order.setIsDiscountPercentage(5.0);
        } else {
            order.setIsDiscountPercentage(0);
        }
        orderRepo.save(order);
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable Long id){
        Optional<Order> o = orderRepo.findById(id);
        if (o.isEmpty()) {
            return null;
        } else {
            return o.get();
        }
    }

    @PutMapping("/orders/{id}")
    public void getOrderById(@PathVariable Long id, @RequestBody Order order){
        Order o = orderRepo.findById(id).get();
        o.setOrderName(order.getOrderName());
        o.setPrice(order.getPrice());
        o.setDiscounted(order.getIsDiscounted());
        orderRepo.save(o);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepo.deleteById(id);
    }


    @GetMapping("/regbill")
    public ResponseEntity<?> getTotalRegularBill() {
        List<Order> orderItems = orderRepo.findAll();
        RegularBill r = new RegularBill();
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("data", r.getTotalBill(orderItems));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @GetMapping("/discbill")
    public ResponseEntity<?> getTotalDiscountBill() {
        List<Order> orderItems = orderRepo.findAll();
        DiscountedBill d = new DiscountedBill();
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("data", d.getTotalBill(orderItems));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
