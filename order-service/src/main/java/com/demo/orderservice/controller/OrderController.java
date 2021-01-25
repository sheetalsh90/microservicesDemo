package com.demo.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.orderservice.commons.TransactionRequest;
import com.demo.orderservice.commons.TransactionResponse;
import com.demo.orderservice.entity.Order;
import com.demo.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	/*
	 * @PostMapping("/bookOrder") public Order bookOrder(@RequestBody Order order) {
	 * return orderService.save(order); }
	 */

	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest transactionRequest) {
		return orderService.save(transactionRequest);
	}

	@PutMapping("/updateOrder/{id}")
	public Order updateOrder(@PathVariable(name = "id") int id, @RequestBody Order order) {
		return orderService.updateOrder(id, order);
	}

	@DeleteMapping("/deleteOrder/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable(name = "id") int id) {
		Order order = orderService.findOrder(id);
		orderService.deleteOrder(id, order);
		return new ResponseEntity<Object>("Order Deleted Successfully", HttpStatus.OK);

	}
}
