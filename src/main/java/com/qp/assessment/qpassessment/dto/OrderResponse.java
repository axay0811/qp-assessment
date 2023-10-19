package com.qp.assessment.qpassessment.dto;

import java.io.Serializable;
import java.util.List;

public class OrderResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int orderId;
	private List<OrderDetails> orderDetails;
	private Double totalPrice;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
