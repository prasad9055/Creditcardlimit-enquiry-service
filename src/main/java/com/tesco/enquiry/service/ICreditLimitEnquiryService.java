package com.tesco.enquiry.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tesco.enquiry.model.EnquiryRequest;
import com.tesco.enquiry.model.EnquiryResponse;

public interface ICreditLimitEnquiryService {
	
	public String enquiry(EnquiryRequest enquiryRequest);

}
