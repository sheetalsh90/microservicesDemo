package com.demo.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.orderservice.entity.Order;

public interface OrderRepository  extends JpaRepository<Order,Integer>{

}
