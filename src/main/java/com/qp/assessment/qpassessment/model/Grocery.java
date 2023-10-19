package com.qp.assessment.qpassessment.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Grocery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gId;
	private String name;
	private String description;
	private double price;
	private int availableQty;
	private Date createdDttm;
	private Date modifiedDttm;
	
	public Integer getgId() {
		return gId;
	}
	public void setgId(Integer gId) {
		this.gId = gId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getAvailableQty() {
		return availableQty;
	}
	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
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
	
	

}
