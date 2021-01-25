package com.demo.orderservice.commons;

import com.demo.orderservice.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
private Order order;
private Payment payment;
}
