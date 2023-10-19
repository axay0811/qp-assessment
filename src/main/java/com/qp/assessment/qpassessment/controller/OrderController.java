package com.qp.assessment.qpassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.assessment.qpassessment.dto.OrderRequest;
import com.qp.assessment.qpassessment.dto.OrderResponse;
import com.qp.assessment.qpassessment.exceptions.InvalidRequestException;
import com.qp.assessment.qpassessment.service.OrderService;
import com.qp.assessment.qpassessment.service.RequestValidator;

@RestController
@RequestMapping("orders")
public class OrderController {
	
	@Autowired
	private RequestValidator requestValidator;
	
	@Autowired
	private OrderService orderService;

	@PostMapping("book-order")
	public ResponseEntity<OrderResponse> bookOrder(@RequestBody OrderRequest orderRequest) {
		
		if (!requestValidator.validateOrderRequest(orderRequest)) {
			throw new InvalidRequestException();
		}
		
		return orderService.bookOrder(orderRequest);
	}
	
	@GetMapping("history/{id}")
	public ResponseEntity<List<OrderResponse>> getOrderHistoryForUser(@PathVariable int id){
		
		if (id == 0)
			throw new InvalidRequestException();
		
		return orderService.getOrderHistoryForUser(id);
	}
}
