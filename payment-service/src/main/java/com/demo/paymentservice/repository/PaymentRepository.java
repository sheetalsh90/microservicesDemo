package com.demo.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByOrderId(int orderId);

}
