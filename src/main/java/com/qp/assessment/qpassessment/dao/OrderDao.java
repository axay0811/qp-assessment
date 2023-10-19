package com.qp.assessment.qpassessment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qp.assessment.qpassessment.model.Orders;
import com.qp.assessment.qpassessment.model.Users;
import com.qp.assessment.qpassessment.repo.OrderRepo;

@Repository
public class OrderDao {
	
	@Autowired
	private OrderRepo orderRepo;

	public Orders saveOrder(Orders order) {
		return orderRepo.save(order);
		
	}
	
	public List<Orders> getOrdersForUser(Users user){
		return orderRepo.findByUser(user);
	}
}
