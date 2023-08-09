package com.tesco.enquiry.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Component
@JsonInclude (value = Include.NON_NULL)
public class EnquiryResponse {
	
	
	private StatusBlock statusBlock ;
	private CustomerInfo customerInfo ;

}
