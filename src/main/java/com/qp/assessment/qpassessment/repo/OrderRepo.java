package com.qp.assessment.qpassessment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qp.assessment.qpassessment.model.Orders;
import com.qp.assessment.qpassessment.model.Users;

public interface OrderRepo extends JpaRepository<Orders, Integer>{

	List<Orders> findByUser(Users user);
}
