package com.accenture.ws.repository;

import com.accenture.ws.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order,Long> {

}
