package com.demo.paymentservice.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.paymentservice.entity.Payment;
import com.demo.paymentservice.repository.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	public Payment save(Payment payment) {
		payment.setTransactionId(UUID.randomUUID().toString());
		payment.setPaymentStatus(paymentProcession());
		return paymentRepository.save(payment);
	}
	
	public String paymentProcession() {
		return new Random().nextBoolean()?"success":"false";
	}

	public Payment findPaymentByOrderId(int orderId) {
		return paymentRepository.findByOrderId(orderId);
	}

}
