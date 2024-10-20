package com.ebayfff.dao;

import java.util.List;

import com.ebayfff.dao.GenericDAO;
import com.ebayfff.domain.User;

import java.util.Optional;




public interface UserDAO extends GenericDAO<User, Integer> {
  
	List<User> findAll();
	






}


