package com.qp.assessment.qpassessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qp.assessment.qpassessment.dao.OrderDao;
import com.qp.assessment.qpassessment.dto.OrderDetails;
import com.qp.assessment.qpassessment.dto.OrderRequest;
import com.qp.assessment.qpassessment.dto.OrderResponse;
import com.qp.assessment.qpassessment.exceptions.BadSelectionException;
import com.qp.assessment.qpassessment.exceptions.InsufficientInventoryException;
import com.qp.assessment.qpassessment.mappers.OrderDetailsMapper;
import com.qp.assessment.qpassessment.mappers.OrderMapper;
import com.qp.assessment.qpassessment.model.Grocery;
import com.qp.assessment.qpassessment.model.Orders;
import com.qp.assessment.qpassessment.model.Users;

@Service
public class OrderService {

	@Autowired
	private GroceryService groceryService;

	@Autowired
	private OrderDetailsMapper orderDetailsMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderDetailsService orderDetailsService;

	public ResponseEntity<OrderResponse> bookOrder(OrderRequest orderRequest) {

		List<com.qp.assessment.qpassessment.model.OrderDetails> modelGds = new ArrayList<>();

		List<Integer> groceryIds = orderRequest.getOrderDetails().stream().map(gd -> gd.getGrocery().getId())
				.collect(Collectors.toList());

		List<Grocery> groceries = groceryService.getGroceriesByIds(groceryIds);

		for (Grocery grocery : groceries) {
			OrderDetails orderDetails = orderRequest.getOrderDetails().stream()
					.filter(gd -> gd.getGrocery().getId() == grocery.getgId()).findFirst().orElse(null);

			if (Objects.isNull(orderDetails)) {
				throw new BadSelectionException();
			}

			if (orderDetails.getQuantity() > grocery.getAvailableQty()) {
				throw new InsufficientInventoryException();
			}

			grocery.setAvailableQty(grocery.getAvailableQty() - orderDetails.getQuantity());
			com.qp.assessment.qpassessment.model.OrderDetails modelGd = orderDetailsMapper.toModel(orderDetails);
			modelGd.setGroceries(grocery);
			modelGds.add(modelGd);

		}

		List<com.qp.assessment.qpassessment.model.OrderDetails> orderDetailsList = orderDetailsService
				.saveOrderDetails(modelGds);
		Double totalPrice = this.calculatePrice(orderRequest, groceries);
		Users user = userService.getUserById(orderRequest.getUserId());
		Orders orderToBeSaved = orderMapper.createOrder(user, orderDetailsList, totalPrice);
		Orders savedOrder = this.saveOrder(orderToBeSaved);
		OrderResponse orderResponse = orderMapper.createOrderResponse(orderRequest, savedOrder.getOrderId(),
				totalPrice);

		return new ResponseEntity<OrderResponse>(orderResponse, HttpStatus.OK);
	}

	private Orders saveOrder(Orders order) {

		return orderDao.saveOrder(order);

	}

	public double calculatePrice(OrderRequest orderRequest, List<Grocery> groceries) {
		Double totalCost = 0.0;
		for (OrderDetails gd : orderRequest.getOrderDetails()) {
			double price = groceries.stream().filter(g -> g.getgId() == gd.getGrocery().getId()).findFirst().get()
					.getPrice();
			totalCost += (gd.getQuantity() * price);
		}

		return Math.round(totalCost);
	}

	public ResponseEntity<List<OrderResponse>> getOrderHistoryForUser(int id) {
		List<OrderResponse> historyOrders = new ArrayList<>();
		Users user = userService.getUserById(id);
		List<Orders> orders = orderDao.getOrdersForUser(user);

		for (Orders order : orders) {
			historyOrders.add(orderMapper.createOrderResponse(order));
		}

		return new ResponseEntity<List<OrderResponse>>(historyOrders, HttpStatus.OK);
	}

}
