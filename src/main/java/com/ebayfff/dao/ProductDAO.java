package com.ebayfff.dao;

import java.util.List;

import com.ebayfff.dao.GenericDAO;
import com.ebayfff.domain.Product;





public interface ProductDAO extends GenericDAO<Product, Integer> {
  
	List<Product> findAll();
	






}


