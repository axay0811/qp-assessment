package com.qp.assessment.qpassessment.mappers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qp.assessment.qpassessment.dto.OrderRequest;
import com.qp.assessment.qpassessment.dto.OrderResponse;
import com.qp.assessment.qpassessment.model.Orders;
import com.qp.assessment.qpassessment.model.Users;

@Component
public class OrderMapper {

	@Autowired
	private OrderDetailsMapper orderDetailsMapper;

	public Orders createOrder(Users user, List<com.qp.assessment.qpassessment.model.OrderDetails> modelGds,
			Double totalPrice) {
		Orders order = new Orders();
		order.setCreatedDttm(new Date());
		order.setModifiedDttm(new Date());
		order.setOrderDetails(modelGds);
		order.setTotalPrice(totalPrice);
		order.setUser(user);

		return order;
	}

	public OrderResponse createOrderResponse(OrderRequest orderRequest, Integer orderId, Double totalPrice) {

		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderDetails(orderRequest.getOrderDetails());
		orderResponse.setOrderId(orderId);
		orderResponse.setTotalPrice(totalPrice);
		return orderResponse;
	}

	public OrderResponse createOrderResponse(Orders order) {

		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderDetails(orderDetailsMapper.toDtos(order.getOrderDetails()));
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setTotalPrice(order.getTotalPrice());
		return orderResponse;
	}

}
