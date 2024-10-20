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

import com.ebayfff.domain.Review;
import com.ebayfff.dto.ReviewDTO;
import com.ebayfff.dto.ReviewSearchDTO;
import com.ebayfff.dto.ReviewPageDTO;
import com.ebayfff.service.ReviewService;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/review")
@RestController
public class ReviewController {

	private final static Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Autowired
	ReviewService reviewService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Review> getAll() {

		List<Review> reviews = reviewService.findAll();
		
		return reviews;	
	}

	//@ReadAccess
	@GetMapping(value = "/{reviewId}")
	@ResponseBody
	public ReviewDTO getReview(@PathVariable Integer reviewId) {
		
		return (reviewService.getReviewDTOById(reviewId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addReview", method = RequestMethod.POST)
	public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reviewService.addReview(reviewDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/reviews")
	public ResponseEntity<ReviewPageDTO> getReviews(ReviewSearchDTO reviewSearchDTO) {
 
		return reviewService.getReviews(reviewSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updateReview", method = RequestMethod.POST)
	public ResponseEntity<?> updateReview(@RequestBody ReviewDTO reviewDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reviewService.updateReview(reviewDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
