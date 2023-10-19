package com.qp.assessment.qpassessment.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qp.assessment.qpassessment.dto.Grocery;

@Component
public class GroceryMapper {

	public Grocery toDto(com.qp.assessment.qpassessment.model.Grocery modelGrocery) {
		Grocery grocery = new Grocery();

		grocery.setId(modelGrocery.getgId());
		grocery.setName(modelGrocery.getName());
		grocery.setPrice(modelGrocery.getPrice());
		grocery.setQty(modelGrocery.getAvailableQty());
		grocery.setDescription(modelGrocery.getDescription());

		return grocery;
	}

	public List<Grocery> toDtos(List<com.qp.assessment.qpassessment.model.Grocery> modelGroceries) {
		List<Grocery> groceries = new ArrayList<>();

		for (com.qp.assessment.qpassessment.model.Grocery grocery : modelGroceries) {
			groceries.add(this.toDto(grocery));
		}

		return groceries;
	}

	public com.qp.assessment.qpassessment.model.Grocery toModel(Grocery grocery) {
		com.qp.assessment.qpassessment.model.Grocery modelGrocery = new com.qp.assessment.qpassessment.model.Grocery();

		modelGrocery.setAvailableQty(grocery.getQty());
		modelGrocery.setDescription(grocery.getDescription());
		modelGrocery.setgId(grocery.getId());
		modelGrocery.setPrice(grocery.getPrice());
		modelGrocery.setName(grocery.getName());

		return modelGrocery;
	}

	public List<com.qp.assessment.qpassessment.model.Grocery> toModels(List<Grocery> groceries) {
		List<com.qp.assessment.qpassessment.model.Grocery> modelGroceries = new ArrayList<>();

		for (Grocery grocery : groceries) {
			modelGroceries.add(this.toModel(grocery));
		}

		return modelGroceries;
	}
}
