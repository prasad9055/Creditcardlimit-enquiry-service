package com.tesco.enquiry.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class StatusBlock {

	private String respCode;
	private String respMsg;
}
