package com.tesco.enquiry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.function.EntityResponse;

import com.tesco.enquiry.exception.CreditLimitEnquiryRequestInvalidException;
import com.tesco.enquiry.model.EnquiryResponse;
import com.tesco.enquiry.model.StatusBlock;

@ControllerAdvice
public class CreditLimitEnquiryAdvice {
	
	 private static final Logger logger = LoggerFactory.getLogger(CreditLimitEnquiryAdvice.class);
	
	@ExceptionHandler(value=CreditLimitEnquiryRequestInvalidException.class)
	@ResponseBody
	public ResponseEntity<EnquiryResponse> handleRequestInvalidException(CreditLimitEnquiryRequestInvalidException exception) {
		
		EnquiryResponse enquiryResponse = new EnquiryResponse();
		StatusBlock statusBlock = new StatusBlock();
		statusBlock.setRespCode(exception.getRespCode());
		statusBlock.setRespMsg(exception.getRespMsg());
		enquiryResponse.setStatusBlock(statusBlock);
		MDC.put("error_code", statusBlock.getRespCode());
		MDC.put("error_msg", statusBlock.getRespMsg());
		logger.error("Error from invalid Enquiry Request");
       return  new ResponseEntity(enquiryResponse ,HttpStatus.BAD_REQUEST) ;
}
	}
