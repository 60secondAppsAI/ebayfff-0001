package com.ebayfff.dao;

import java.util.List;

import com.ebayfff.dao.GenericDAO;
import com.ebayfff.domain.PaymentDetail;





public interface PaymentDetailDAO extends GenericDAO<PaymentDetail, Integer> {
  
	List<PaymentDetail> findAll();
	






}


