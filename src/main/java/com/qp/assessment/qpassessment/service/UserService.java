package com.qp.assessment.qpassessment.service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qp.assessment.qpassessment.dao.UserDao;
import com.qp.assessment.qpassessment.dto.User;
import com.qp.assessment.qpassessment.exceptions.InvalidRequestException;
import com.qp.assessment.qpassessment.mappers.UserMapper;
import com.qp.assessment.qpassessment.model.Users;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserMapper userMapper;

	

	public ResponseEntity<String> addNewUser(User user) {

		Users modelUser = userMapper.toModel(user);
		modelUser.setCreatedDttm(new Date());

		if (Objects.nonNull(userDao.saveUser(modelUser)))
			return new ResponseEntity<String>("User added successfully", HttpStatus.CREATED);

		return new ResponseEntity<String>("Failed to add user", HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	public Users getUserById(int id) {
		Optional<Users> user = userDao.findById(id);
		
		if (user.isEmpty())
			throw new InvalidRequestException();
		
		return user.get();
	}

}
