package com.accenture.ws.entity;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_name")
    private String orderName;
    @Column(name = "price")
    private double price;
    @Column(name = "is_discounted")
    private Boolean isDiscounted;
    @Column(name = "is_discount_percentage")
    private double isDiscountPercentage = 5.0;

    public Order() {
    }

    public Order(Long id, String orderName, double price, Boolean isDiscounted) {
        this.id = id;
        this.orderName = orderName;
        this.price = price;
        this.isDiscounted = isDiscounted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getIsDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(Boolean discounted) {
        isDiscounted = discounted;
    }

    public double getIsDiscountPercentage() {
        return isDiscountPercentage;
    }

    public void setIsDiscountPercentage(double isDiscountPercentage) {
        this.isDiscountPercentage = isDiscountPercentage;
    }
}
