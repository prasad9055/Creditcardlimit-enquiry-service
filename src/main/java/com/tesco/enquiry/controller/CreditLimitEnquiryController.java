package com.tesco.enquiry.controller;

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
	
	@GetMapping("/enquiry/{promocode}")
	@ResponseBody
	public ResponseEntity<EnquiryResponse>	enquiry(@PathVariable("promocode") String promocode ,
			                            @RequestHeader("client_id") String client_id ,
			                            @RequestHeader("channel_id") String channel_id ,
			                            @RequestHeader("message_ts") String messageTimeStamp ,
			                            @RequestHeader("request_id") String request_id ) {
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
		
		
		
		return new ResponseEntity(enquiryResponse , HttpStatus.OK);
	}

}
