package com.qp.assessment.qpassessment.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer detailsId;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "grocery_id", referencedColumnName = "gId")
	private Grocery grocery;

	private int quantity;

	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	public Grocery getGroceries() {
		return grocery;
	}

	public void setGroceries(Grocery grocery) {
		this.grocery = grocery;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
