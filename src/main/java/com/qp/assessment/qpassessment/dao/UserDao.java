package com.qp.assessment.qpassessment.dao;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qp.assessment.qpassessment.model.Users;
import com.qp.assessment.qpassessment.repo.UserRepo;

@Repository
public class UserDao {

	@Autowired
	private UserRepo userRepo;
	
	public Optional<Users> findById(int id) {
		return userRepo.findById(id);
	}
	
	public Users saveUser(Users user) {
		setDates(user);
		return userRepo.save(user);
	}
	
	private void setDates(Users user) {
		user.setModifiedDttm(new Date());
	}
}
