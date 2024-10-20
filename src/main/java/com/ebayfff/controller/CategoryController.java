package com.ebayfff.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.ebayfff.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.ebayfff.domain.Category;
import com.ebayfff.dto.CategoryDTO;
import com.ebayfff.dto.CategorySearchDTO;
import com.ebayfff.dto.CategoryPageDTO;
import com.ebayfff.service.CategoryService;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/category")
@RestController
public class CategoryController {

	private final static Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	CategoryService categoryService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Category> getAll() {

		List<Category> categorys = categoryService.findAll();
		
		return categorys;	
	}

	//@ReadAccess
	@GetMapping(value = "/{categoryId}")
	@ResponseBody
	public CategoryDTO getCategory(@PathVariable Integer categoryId) {
		
		return (categoryService.getCategoryDTOById(categoryId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = categoryService.addCategory(categoryDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/categorys")
	public ResponseEntity<CategoryPageDTO> getCategorys(CategorySearchDTO categorySearchDTO) {
 
		return categoryService.getCategorys(categorySearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO categoryDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = categoryService.updateCategory(categoryDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
