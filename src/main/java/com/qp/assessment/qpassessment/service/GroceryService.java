package com.qp.assessment.qpassessment.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.qp.assessment.qpassessment.dao.GroceryDao;
import com.qp.assessment.qpassessment.exceptions.FailedToAddException;
import com.qp.assessment.qpassessment.exceptions.FailedToUpdateException;
import com.qp.assessment.qpassessment.mappers.GroceryMapper;
import com.qp.assessment.qpassessment.model.Grocery;

@Service
public class GroceryService {

	@Autowired
	private GroceryDao groceryDao;

	@Autowired
	private GroceryMapper groceryMapper;

	public ResponseEntity<List<com.qp.assessment.qpassessment.dto.Grocery>> getAllGroceries() {

		List<Grocery> groceries = groceryDao.getAllGroceries();

		return new ResponseEntity<List<com.qp.assessment.qpassessment.dto.Grocery>>(groceryMapper.toDtos(groceries),
				HttpStatus.OK);
	}

	public ResponseEntity<List<com.qp.assessment.qpassessment.dto.Grocery>> addGroceries(
			List<com.qp.assessment.qpassessment.dto.Grocery> groceries) {

		List<Grocery> groceriesToBeAdded = groceryMapper.toModels(groceries);
		groceriesToBeAdded.stream().forEach(g -> g.setCreatedDttm(new Date()));
		List<Grocery> addedGroceries = groceryDao.saveGroceries(groceriesToBeAdded);
		
		if (CollectionUtils.isEmpty(addedGroceries)) {
			throw new FailedToAddException();
		}

		return new ResponseEntity<List<com.qp.assessment.qpassessment.dto.Grocery>>(
				groceryMapper.toDtos(addedGroceries), HttpStatus.CREATED);
	}

	public ResponseEntity<List<com.qp.assessment.qpassessment.dto.Grocery>> updateGroceries(
			List<com.qp.assessment.qpassessment.dto.Grocery> groceries) {
		List<Grocery> groceriesToBeUpdated = groceryMapper.toModels(groceries);
		
		List<Grocery> groceriesInDB = groceryDao
				.findByIds(groceriesToBeUpdated.stream().map(g -> g.getgId()).collect(Collectors.toList()));
		
		for (Grocery grocery : groceriesToBeUpdated) {
			Optional<Grocery> dbGrocery = groceriesInDB.stream().filter(g -> g.getgId() == grocery.getgId())
					.findFirst();
			
			grocery.setCreatedDttm(dbGrocery.get().getCreatedDttm());
		}
		List<Grocery> updatedGroceries = groceryDao.saveGroceries(groceriesToBeUpdated);

		if (CollectionUtils.isEmpty(updatedGroceries)) {
			throw new FailedToUpdateException();
		}

		return new ResponseEntity<List<com.qp.assessment.qpassessment.dto.Grocery>>(
				groceryMapper.toDtos(updatedGroceries), HttpStatus.OK);
	}

	public ResponseEntity<String> removeGroceries(List<Integer> ids) {
		
		groceryDao.deleteByIds(ids);
		
		return new ResponseEntity<String>("Groceries deleted successfully", HttpStatus.OK);
	}
	
	public List<Grocery> getGroceriesByIds(List<Integer> ids){
		return groceryDao.findByIds(ids);
	}

}
