package com.tesco.enquiry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tesco.enquiry.model.EnquiryRequest;
import com.tesco.enquiry.model.EnquiryResponse;
import com.tesco.enquiry.service.ICreditLimitEnquiryService;
import com.tesco.enquiry.validator.CreditLimitEnquiryValidator;

@RestController
@RequestMapping("/v1")
public class CreditLimitEnquiryController {
	
	@Autowired
	CreditLimitEnquiryValidator creditLimitEnquiryValidator ;
	@Autowired
	ICreditLimitEnquiryService creditLimitService ;
	
	 private static final Logger logger = LoggerFactory.getLogger(CreditLimitEnquiryController.class);
	
	@GetMapping("/enquiry/{promocode}")
	@ResponseBody
	public ResponseEntity<EnquiryResponse>	enquiry(@PathVariable("promocode") String promocode ,
			                            @RequestHeader("client_id") String client_id ,
			                            @RequestHeader("channel_id") String channel_id ,
			                            @RequestHeader("message_ts") String messageTimeStamp ,
			                            @RequestHeader("request_id") String request_id ) {
		MDC.put("promocode", promocode);
		MDC.put("request_id", request_id);
		MDC.put("client_id", client_id);
		//write info logs
		logger.debug("Enter a Enquiry Method");
		logger.info("i/P=>"+request_id);
		//1 Get Request From Consumer 
	
		EnquiryRequest enquiryRequest = new EnquiryRequest();
		enquiryRequest.setPromocode(promocode);
		enquiryRequest.setClientId(client_id);
		enquiryRequest.setChannelId(channel_id);
		enquiryRequest.setRequestId(request_id);
		enquiryRequest.setMessageTs(messageTimeStamp);
		//2 Validate the Request
		                       
		creditLimitEnquiryValidator.validateRequest(enquiryRequest);
		
		//prepare the Request For Service Class
		
		
		//call Service Call and get Response 
	EnquiryResponse	enquiryResponse = creditLimitService.enquiry(enquiryRequest);
	 logger.debug("Exit the Enquiry Method");
	 logger.info("Exit the Enquiry Method");
		return new ResponseEntity(enquiryResponse , HttpStatus.OK);
	}

}
