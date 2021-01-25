package com.demo.orderservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.orderservice.commons.Payment;
import com.demo.orderservice.commons.TransactionRequest;
import com.demo.orderservice.commons.TransactionResponse;
import com.demo.orderservice.entity.Order;
import com.demo.orderservice.exception.OrderNotFoundException;
import com.demo.orderservice.repository.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RestTemplate restTemplate;

	/*
	 * public Order save(Order order) { return orderRepository.save(order); }
	 */
	
	public TransactionResponse save(TransactionRequest request) {
		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payments/doPayment", payment,
				Payment.class);
		response = paymentResponse.getPaymentStatus().equals("success") ? "Order is placed successfully"
				: "Payment Failure";
		orderRepository.save(order);
		return new TransactionResponse(order, paymentResponse.getTransactionId(), paymentResponse.getAmount(),
				response);

	}
	public Order updateOrder(int id ,Order updatedOrder) {
		Optional<Order> order = orderRepository.findById(id);
		if (!order.isPresent())
			throw new OrderNotFoundException();
		return orderRepository.save(updatedOrder);

	}

	public void deleteOrder(int id, Order updatedOrder) {
		Optional<Order> order = orderRepository.findById(id);
		if (!order.isPresent())
			throw new OrderNotFoundException();
		orderRepository.delete(updatedOrder);
	}
	
	public Order findOrder(int id) {
		Optional<Order> order= orderRepository.findById(id);
		if(!order.isPresent()) throw new OrderNotFoundException();
		return order.get();
	}

}
