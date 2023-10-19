package com.qp.assessment.qpassessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qp.assessment.qpassessment.dao.OrderDetailsDao;
import com.qp.assessment.qpassessment.model.OrderDetails;

@Service
public class OrderDetailsService {
	
	@Autowired
	private OrderDetailsDao orderDetailsDao;

	public List<OrderDetails> saveOrderDetails(List<OrderDetails> details){
		return orderDetailsDao.saveOrderDetailsList(details);
	}
}
