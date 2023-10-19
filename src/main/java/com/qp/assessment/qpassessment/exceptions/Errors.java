package com.qp.assessment.qpassessment.exceptions;

import java.util.Date;

public class Errors {

	private Integer errorCode;
	private String errorMsg;
	private Date errorDate;

	public Errors(Integer errorCode, String errorMsg, Date errorDate) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorDate = errorDate;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Date getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(Date errorDate) {
		this.errorDate = errorDate;
	}

}
