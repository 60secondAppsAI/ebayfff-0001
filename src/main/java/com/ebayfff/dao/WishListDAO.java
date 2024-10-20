package com.ebayfff.dao;

import java.util.List;

import com.ebayfff.dao.GenericDAO;
import com.ebayfff.domain.WishList;





public interface WishListDAO extends GenericDAO<WishList, Integer> {
  
	List<WishList> findAll();
	






}


