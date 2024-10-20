package com.ebayfff.dao;

import java.util.List;

import com.ebayfff.dao.GenericDAO;
import com.ebayfff.domain.Order;





public interface OrderDAO extends GenericDAO<Order, Integer> {
  
	List<Order> findAll();
	






}


