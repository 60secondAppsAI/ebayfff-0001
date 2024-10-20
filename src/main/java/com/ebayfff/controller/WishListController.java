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

import com.ebayfff.domain.WishList;
import com.ebayfff.dto.WishListDTO;
import com.ebayfff.dto.WishListSearchDTO;
import com.ebayfff.dto.WishListPageDTO;
import com.ebayfff.service.WishListService;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/wishList")
@RestController
public class WishListController {

	private final static Logger logger = LoggerFactory.getLogger(WishListController.class);

	@Autowired
	WishListService wishListService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<WishList> getAll() {

		List<WishList> wishLists = wishListService.findAll();
		
		return wishLists;	
	}

	//@ReadAccess
	@GetMapping(value = "/{wishListId}")
	@ResponseBody
	public WishListDTO getWishList(@PathVariable Integer wishListId) {
		
		return (wishListService.getWishListDTOById(wishListId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addWishList", method = RequestMethod.POST)
	public ResponseEntity<?> addWishList(@RequestBody WishListDTO wishListDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = wishListService.addWishList(wishListDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/wishLists")
	public ResponseEntity<WishListPageDTO> getWishLists(WishListSearchDTO wishListSearchDTO) {
 
		return wishListService.getWishLists(wishListSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateWishList", method = RequestMethod.POST)
	public ResponseEntity<?> updateWishList(@RequestBody WishListDTO wishListDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = wishListService.updateWishList(wishListDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
