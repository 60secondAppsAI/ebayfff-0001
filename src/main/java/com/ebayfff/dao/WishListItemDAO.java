package com.ebayfff.dao;

import java.util.List;

import com.ebayfff.dao.GenericDAO;
import com.ebayfff.domain.WishListItem;





public interface WishListItemDAO extends GenericDAO<WishListItem, Integer> {
  
	List<WishListItem> findAll();
	






}


