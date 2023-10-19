package com.qp.assessment.qpassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qp.assessment.qpassessment.dto.Grocery;
import com.qp.assessment.qpassessment.exceptions.InavalidUserRoleException;
import com.qp.assessment.qpassessment.exceptions.InvalidRequestException;
import com.qp.assessment.qpassessment.service.GroceryService;
import com.qp.assessment.qpassessment.service.RequestValidator;

@RestController
@RequestMapping("grocery")
public class GroceryController {

	@Autowired
	private GroceryService groceryService;

	@Autowired
	private RequestValidator requestValidator;

	@GetMapping("existing-grocery")
	public ResponseEntity<List<Grocery>> viewExistingGroceries() {
		return groceryService.getAllGroceries();
	}

	@PostMapping("new-groceries/{userId}")
	public ResponseEntity<List<Grocery>> addNewGroceries(@RequestBody List<Grocery> groceries,
			@PathVariable int userId) {

		if (!requestValidator.isAdmin(userId)) {
			throw new InavalidUserRoleException();
		}
		
		if (!requestValidator.validateAddGroceriesRequest(groceries)) {
			throw new InvalidRequestException();
		}
		
		return groceryService.addGroceries(groceries);

	}
	
	@PutMapping("details-update/{userId}")
	public ResponseEntity<List<Grocery>> updateGroceries(@RequestBody List<Grocery> groceries,
			@PathVariable int userId){
		
		if (!requestValidator.isAdmin(userId)) {
			throw new InavalidUserRoleException();
		}
		
		if (!requestValidator.validateUpdateGroceriesRequest(groceries)) {
			throw new InvalidRequestException();
		}
		
		return groceryService.updateGroceries(groceries);
	}
	
	@DeleteMapping("byIds/{userId}")
	public ResponseEntity<String> removeGroceries(@RequestBody List<Integer> ids,
			@PathVariable int userId){
		
		if (!requestValidator.isAdmin(userId)) {
			throw new InavalidUserRoleException();
		}
		
		if (CollectionUtils.isEmpty(ids)) {
			throw new InvalidRequestException();
		}
		
		return groceryService.removeGroceries(ids);
	}

}
