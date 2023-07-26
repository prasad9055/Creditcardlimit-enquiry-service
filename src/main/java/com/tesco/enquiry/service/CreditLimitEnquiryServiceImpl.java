package com.tesco.enquiry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tesco.enquiry.intg.dao.ICreditLimitEnquiryDao;
import com.tesco.enquiry.model.CustomerInfo;
import com.tesco.enquiry.model.EnquiryDaoRequest;
import com.tesco.enquiry.model.EnquiryDaoResponse;
import com.tesco.enquiry.model.EnquiryRequest;
import com.tesco.enquiry.model.EnquiryResponse;

@Service
public class CreditLimitEnquiryServiceImpl implements ICreditLimitEnquiryService {

	@Autowired
	ICreditLimitEnquiryDao iCreditLimitEnquiryDao;

	@Autowired
	EnquiryDaoRequest enquiryDaoRequest;

	public EnquiryResponse enquiry(EnquiryRequest enquiryRequest) {
		// TODO Auto-generated method stub
		enquiryDaoRequest.setPromocode(enquiryRequest.getPromocode());
		enquiryDaoRequest.setClientId(enquiryRequest.getClientId());
		enquiryDaoRequest.setChannelId(enquiryRequest.getChannelId());
		enquiryDaoRequest.setMessageTs(enquiryRequest.getMessageTs());
		enquiryDaoRequest.setRequestId(enquiryRequest.getRequestId());

		EnquiryDaoResponse enquiryDaoResponse = iCreditLimitEnquiryDao.enquiry(enquiryDaoRequest);

		EnquiryResponse enquiryResponse = new EnquiryResponse();
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setAvailableAmount(enquiryDaoResponse.getAvailableAmount());
		customerInfo.setCardNum(enquiryDaoResponse.getCardNum());
		customerInfo.setCvv(enquiryDaoResponse.getCvv());
		customerInfo.setIncreaseAmount(enquiryDaoResponse.getIncreaseAmount());
		customerInfo.setIncreasePer(enquiryDaoResponse.getIncreasePer());

		enquiryResponse.setCustomerInfo(customerInfo);

		return enquiryResponse;
	}

}
