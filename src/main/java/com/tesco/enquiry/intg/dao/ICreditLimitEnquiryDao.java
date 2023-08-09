package com.tesco.enquiry.intg.dao;

import com.tesco.enquiry.model.EnquiryDaoRequest;
import com.tesco.enquiry.model.EnquiryDaoResponse;

public interface ICreditLimitEnquiryDao {

	public EnquiryDaoResponse enquiry(EnquiryDaoRequest enquiryDaoRequest);
}
