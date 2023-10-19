package com.qp.assessment.qpassessment.dto;

public class OrderDetails {
	
	private int id;
	private Grocery grocery;
	private int quantity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Grocery getGrocery() {
		return grocery;
	}
	public void setGrocery(Grocery grocery) {
		this.grocery = grocery;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
