package com.tesco.enquiry.service;

import com.tesco.enquiry.model.EnquiryRequest;
import com.tesco.enquiry.model.EnquiryResponse;

public interface ICreditLimitEnquiryService {
	
	public EnquiryResponse enquiry(EnquiryRequest enquiryRequest);

}
