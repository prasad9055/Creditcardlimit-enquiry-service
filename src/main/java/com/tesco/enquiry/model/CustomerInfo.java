package com.tesco.enquiry.model;

import lombok.Data;

@Data
public class CustomerInfo {


	private String cardNum ;
	private String cvv;
	private long availableAmount ; 
	private long increaseAmount ;
	private float increasePer ;
	
}
