package com.qp.assessment.qpassessment.dto;

import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private int userId;
	private List<OrderDetails> orderDetails;

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
