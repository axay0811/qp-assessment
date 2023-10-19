package com.qp.assessment.qpassessment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qp.assessment.qpassessment.model.Users;

public interface UserRepo extends JpaRepository<Users, Integer>{

}
