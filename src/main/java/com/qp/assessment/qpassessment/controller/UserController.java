package com.qp.assessment.qpassessment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.assessment.qpassessment.dto.User;
import com.qp.assessment.qpassessment.service.RequestValidator;
import com.qp.assessment.qpassessment.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestValidator requestValidator;

	@PostMapping("new")
	public ResponseEntity<String> addNewUser(@RequestBody User user) {

		requestValidator.validateAddNewUserRequest(user);

		return userService.addNewUser(user);
	}
	
}
