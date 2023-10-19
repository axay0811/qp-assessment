package com.qp.assessment.qpassessment.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = UnAuthorizedUserException.class)
	public ResponseEntity<Errors> handleUnAuthorizedUserException() {

		Errors error = new Errors(401, "Unauthorized Request, please change role to user", new Date());
		return new ResponseEntity<Errors>(error, HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(value = InavalidUserRoleException.class)
	public ResponseEntity<Errors> handleInvalidUserRoleException() {
		Errors error = new Errors(401, "Invalid User Role for Operation, Only admin can perform this operation",
				new Date());
		return new ResponseEntity<Errors>(error, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = InvalidRequestException.class)
	public ResponseEntity<Errors> handleInvalidRequestException() {
		Errors error = new Errors(400, "Invalid Request, please correct the request and try again", new Date());
		return new ResponseEntity<Errors>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = FailedToAddException.class)
	public ResponseEntity<Errors> handleFailedToAddException() {
		Errors error = new Errors(417, "Failed to persist record in DB, please try again", new Date());
		return new ResponseEntity<Errors>(error, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(value = FailedToUpdateException.class)
	public ResponseEntity<Errors> handleFailedToUpdateException() {
		Errors error = new Errors(304, "Failed to update record in DB, please try again", new Date());
		return new ResponseEntity<Errors>(error, HttpStatus.NOT_MODIFIED);
	}
	
	@ExceptionHandler(value = BadSelectionException.class)
	public ResponseEntity<Errors> handleBadSelectionException() {
		Errors error = new Errors(404, "Bad selection, Record does not exist in DB", new Date());
		return new ResponseEntity<Errors>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InsufficientInventoryException.class)
	public ResponseEntity<Errors> handleInsufficientInventoryException() {
		Errors error = new Errors(400, "Insufficient Inventory", new Date());
		return new ResponseEntity<Errors>(error, HttpStatus.BAD_REQUEST);
	}
	

}
