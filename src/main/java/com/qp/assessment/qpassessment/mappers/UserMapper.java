package com.qp.assessment.qpassessment.mappers;

import org.springframework.stereotype.Component;

import com.qp.assessment.qpassessment.dto.User;
import com.qp.assessment.qpassessment.model.Users;

@Component
public class UserMapper {

	public Users toModel(User user) {
		Users modelUser = new Users();
		modelUser.setUserId(user.getId());
		modelUser.setAddress(user.getAddress());
		modelUser.setEmailId(user.getEmailId());
		modelUser.setMobNumber(user.getMobNumber());
		modelUser.setName(user.getName());
		modelUser.setRole(user.getRole());

		return modelUser;
	}
}
