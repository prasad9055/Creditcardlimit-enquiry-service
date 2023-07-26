package com.tesco.enquiry.validator;

import org.springframework.stereotype.Component;

import com.tesco.enquiry.exception.CreditLimitEnquiryRequestInvalidException;
import com.tesco.enquiry.model.EnquiryRequest;

@Component
public class CreditLimitEnquiryValidator {

	public void validateRequest(EnquiryRequest enquiryRequest) throws CreditLimitEnquiryRequestInvalidException {
		// TODO validate the request . if request is valid nothing return 
		
		System.out.println("promo code"+ enquiryRequest.getPromocode());
		if(enquiryRequest.getPromocode() == null || enquiryRequest.getPromocode().isEmpty() || enquiryRequest.getPromocode().length() < 11 ) {
			
			throw new CreditLimitEnquiryRequestInvalidException("enq001", "Invalid Promo Code ");
		}
		
	if(enquiryRequest.getClientId()== null ||"".equals(enquiryRequest.getClientId())) {
			
			throw new CreditLimitEnquiryRequestInvalidException("enq001", "Invalid Client Id ");
		}
		//else return user defined Exception 
		
		
	}

}
