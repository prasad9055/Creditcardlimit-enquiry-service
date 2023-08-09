package com.tesco.enquiry.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class EnquiryDaoRequest {
	
	private String promocode ;
	private String clientId;
	private String channelId;
	private String messageTs;
	private String requestId;


}
