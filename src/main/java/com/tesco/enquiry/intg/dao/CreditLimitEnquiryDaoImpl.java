package com.tesco.enquiry.intg.dao;

import org.springframework.stereotype.Component;

import com.tesco.enquiry.model.EnquiryDaoRequest;
import com.tesco.enquiry.model.EnquiryDaoResponse;

@Component
public class CreditLimitEnquiryDaoImpl implements ICreditLimitEnquiryDao {

	@Override
	public EnquiryDaoResponse enquiry(EnquiryDaoRequest enquiryDaoRequest) {
		
		EnquiryDaoResponse enquiryDaoResponse = new EnquiryDaoResponse();
		
		enquiryDaoResponse.setRespCode("0");
		enquiryDaoResponse.setRespMsg("Sucess");
		enquiryDaoResponse.setAvailableAmount(10000);
		enquiryDaoResponse.setCardNum("12345678");
		enquiryDaoResponse.setCvv("4325");
		enquiryDaoResponse.setIncreaseAmount(20000);
		enquiryDaoResponse.setIncreasePer(0.5f);
		
		return enquiryDaoResponse;
		
		}

}
