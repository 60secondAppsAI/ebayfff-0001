package com.ebayfff.dao;

import java.util.List;

import com.ebayfff.dao.GenericDAO;
import com.ebayfff.domain.Bid;





public interface BidDAO extends GenericDAO<Bid, Integer> {
  
	List<Bid> findAll();
	






}


