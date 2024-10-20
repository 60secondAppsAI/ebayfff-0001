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

import com.ebayfff.domain.WishListItem;
import com.ebayfff.dto.WishListItemDTO;
import com.ebayfff.dto.WishListItemSearchDTO;
import com.ebayfff.dto.WishListItemPageDTO;
import com.ebayfff.service.WishListItemService;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/wishListItem")
@RestController
public class WishListItemController {

	private final static Logger logger = LoggerFactory.getLogger(WishListItemController.class);

	@Autowired
	WishListItemService wishListItemService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<WishListItem> getAll() {

		List<WishListItem> wishListItems = wishListItemService.findAll();
		
		return wishListItems;	
	}

	//@ReadAccess
	@GetMapping(value = "/{wishListItemId}")
	@ResponseBody
	public WishListItemDTO getWishListItem(@PathVariable Integer wishListItemId) {
		
		return (wishListItemService.getWishListItemDTOById(wishListItemId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addWishListItem", method = RequestMethod.POST)
	public ResponseEntity<?> addWishListItem(@RequestBody WishListItemDTO wishListItemDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = wishListItemService.addWishListItem(wishListItemDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/wishListItems")
	public ResponseEntity<WishListItemPageDTO> getWishListItems(WishListItemSearchDTO wishListItemSearchDTO) {
 
		return wishListItemService.getWishListItems(wishListItemSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateWishListItem", method = RequestMethod.POST)
	public ResponseEntity<?> updateWishListItem(@RequestBody WishListItemDTO wishListItemDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = wishListItemService.updateWishListItem(wishListItemDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
