package com.qp.assessment.qpassessment.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qp.assessment.qpassessment.model.OrderDetails;

@Component
public class OrderDetailsMapper {

	@Autowired
	private GroceryMapper groceryMapper;

	public OrderDetails toModel(com.qp.assessment.qpassessment.dto.OrderDetails orderDetails) {
		OrderDetails modelGd = new OrderDetails();
		modelGd.setDetailsId(orderDetails.getId());
		modelGd.setQuantity(orderDetails.getQuantity());
		// modelGd.setGroceries(groceryMapper.toModel(groceryDetails.getGrocery()));
		return modelGd;
	}

	public List<OrderDetails> toModels(List<com.qp.assessment.qpassessment.dto.OrderDetails> orderDetails) {
		List<OrderDetails> modelGds = new ArrayList<>();
		for (com.qp.assessment.qpassessment.dto.OrderDetails gDetails : orderDetails) {
			modelGds.add(this.toModel(gDetails));
		}

		return modelGds;
	}

	public com.qp.assessment.qpassessment.dto.OrderDetails toDto(OrderDetails modelDetails) {
		com.qp.assessment.qpassessment.dto.OrderDetails details = new com.qp.assessment.qpassessment.dto.OrderDetails();

		details.setId(modelDetails.getDetailsId());
		details.setQuantity(modelDetails.getQuantity());
		details.setGrocery(groceryMapper.toDto(modelDetails.getGroceries()));

		return details;
	}

	public List<com.qp.assessment.qpassessment.dto.OrderDetails> toDtos(List<OrderDetails> modelDetails) {
		List<com.qp.assessment.qpassessment.dto.OrderDetails> detailsList = new ArrayList<>();

		for (OrderDetails detail : modelDetails) {
			detailsList.add(this.toDto(detail));
		}

		return detailsList;
	}

}
