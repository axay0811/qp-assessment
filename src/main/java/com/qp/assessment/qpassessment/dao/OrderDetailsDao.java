package com.qp.assessment.qpassessment.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qp.assessment.qpassessment.model.OrderDetails;
import com.qp.assessment.qpassessment.repo.OrderDetailsRepo;

@Repository
public class OrderDetailsDao {
	
	@Autowired
	private OrderDetailsRepo orderDetailsRepo;

	public List<OrderDetails> saveOrderDetailsList(List<OrderDetails> details){
		return orderDetailsRepo.saveAll(details);
	}
}
