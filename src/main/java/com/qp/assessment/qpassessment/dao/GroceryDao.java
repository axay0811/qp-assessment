package com.qp.assessment.qpassessment.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qp.assessment.qpassessment.model.Grocery;
import com.qp.assessment.qpassessment.repo.GroceryRepo;

@Repository
public class GroceryDao {
	
	@Autowired
	private GroceryRepo groceryRepo;
	
	public void saveGrocery(Grocery grocery) {
		
		groceryRepo.save(grocery);
	}
	
	public List<Grocery> getAllGroceries(){
		return groceryRepo.findAll();
	}
	
	public List<Grocery> saveGroceries(List<Grocery> groceries){
		groceries.stream().forEach(g -> this.setDates(g));
		return groceryRepo.saveAll(groceries);
	}
	
	public void setDates(Grocery grocery) {
		grocery.setModifiedDttm(new Date());
	}
	
	public void deleteByIds(List<Integer> ids) {
		groceryRepo.deleteAllById(ids);
	}
	
	public List<Grocery> findByIds(List<Integer> ids){
		return groceryRepo.findAllById(ids);
	}

}
