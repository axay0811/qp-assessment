package com.qp.assessment.qpassessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qp.assessment.qpassessment.model.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer>{

}
