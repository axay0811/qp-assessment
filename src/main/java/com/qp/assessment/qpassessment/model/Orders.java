package com.qp.assessment.qpassessment.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private Double totalPrice;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
	private List<OrderDetails> orderDetails;
	
	@ManyToOne(optional = false)
	@JoinColumn(name="user_Id", referencedColumnName = "userId")
	private Users user;
	private Date createdDttm;
	private Date modifiedDttm;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getCreatedDttm() {
		return createdDttm;
	}
	public void setCreatedDttm(Date createdDttm) {
		this.createdDttm = createdDttm;
	}
	public Date getModifiedDttm() {
		return modifiedDttm;
	}
	public void setModifiedDttm(Date modifiedDttm) {
		this.modifiedDttm = modifiedDttm;
	}
	
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}

	
}
