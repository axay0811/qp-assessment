package com.qp.assessment.qpassessment.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qp.assessment.qpassessment.dao.UserDao;
import com.qp.assessment.qpassessment.dto.Grocery;
import com.qp.assessment.qpassessment.dto.OrderRequest;
import com.qp.assessment.qpassessment.dto.User;
import com.qp.assessment.qpassessment.enums.UserRole;
import com.qp.assessment.qpassessment.exceptions.InavalidUserRoleException;
import com.qp.assessment.qpassessment.exceptions.UnAuthorizedUserException;
import com.qp.assessment.qpassessment.model.Users;

@Service
public class RequestValidator {

	@Autowired
	private UserDao userDao;

	public void validateAddNewUserRequest(User user) {
		if (!validateRole(user.getRole()))
			throw new InavalidUserRoleException();

		if (user.getRole().equalsIgnoreCase(UserRole.ADMIN.toString()) && (user.getId() == 0 || !isAdmin(user.getId())))
			throw new UnAuthorizedUserException();

	}

	public boolean validateRole(String role) {

		if (role.equalsIgnoreCase(UserRole.ADMIN.toString()) || role.equalsIgnoreCase(UserRole.USER.toString())) {
			return true;
		}

		return false;

	}

	public boolean isAdmin(int userId) {

		if (userId == 0)
			return false;

		Optional<Users> dbUser = userDao.findById(userId);
		if (dbUser.isPresent()) {
			return dbUser.get().getRole().equalsIgnoreCase(UserRole.ADMIN.toString());
		}
		return false;
	}

	public boolean validateAddGroceriesRequest(List<Grocery> groceries) {

		for (Grocery grocery : groceries) {
			if (Objects.isNull(grocery.getName()))
				return false;

			if (Objects.isNull(grocery.getPrice()) || grocery.getPrice() == 0)
				return false;

			if (Objects.isNull(grocery.getDescription()))
				return false;

			if (Objects.isNull(grocery.getQty()) || grocery.getQty() == 0)
				return false;
		}

		return true;
	}

	public boolean validateUpdateGroceriesRequest(List<Grocery> groceries) {

		for (Grocery grocery : groceries) {
			if (Objects.isNull(grocery.getId()) || grocery.getId() == 0)
				return false;

			if (Objects.isNull(grocery.getName()))
				return false;

			if (Objects.isNull(grocery.getPrice()) || grocery.getPrice() == 0)
				return false;

			if (Objects.isNull(grocery.getDescription()))
				return false;

			if (Objects.isNull(grocery.getQty()))
				return false;
		}

		return true;
	}

	public boolean validateOrderRequest(OrderRequest orderRequest) {
		
		if (Objects.isNull(orderRequest.getUserId()) || orderRequest.getUserId() == 0)
			return false;
		
		if (CollectionUtils.isEmpty(orderRequest.getOrderDetails()))
			return false;
		
		return true;
		
	}

}
