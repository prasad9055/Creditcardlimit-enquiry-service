package com.tesco.enquiry.exception;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreditLimitEnquiryRequestInvalidException extends RuntimeException{

	private String respCode ;
	private String respMsg;
	
	public CreditLimitEnquiryRequestInvalidException(String respCode, String respMsg) {
		this.respCode=respCode;
		this.respMsg=respMsg;
	}
}
