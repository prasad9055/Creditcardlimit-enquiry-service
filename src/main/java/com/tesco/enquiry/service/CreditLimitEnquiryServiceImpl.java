package com.tesco.enquiry.service;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.tesco.enquiry.encrypt.StringToEncrypt;
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

	public String enquiry(EnquiryRequest enquiryRequest){
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
		String plainJson ="";
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			 plainJson = mapper.writeValueAsString(enquiryResponse);
			 StringToEncrypt ste = new StringToEncrypt();
			 plainJson =  ste.encrypt(plainJson);
			 
		} catch (IOException | UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		return plainJson;
	}

}
