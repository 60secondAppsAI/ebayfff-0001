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

import com.ebayfff.domain.PaymentDetail;
import com.ebayfff.dto.PaymentDetailDTO;
import com.ebayfff.dto.PaymentDetailSearchDTO;
import com.ebayfff.dto.PaymentDetailPageDTO;
import com.ebayfff.service.PaymentDetailService;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/paymentDetail")
@RestController
public class PaymentDetailController {

	private final static Logger logger = LoggerFactory.getLogger(PaymentDetailController.class);

	@Autowired
	PaymentDetailService paymentDetailService;



	//@AllowSystem(AuthScopes.READ)
	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PaymentDetail> getAll() {

		List<PaymentDetail> paymentDetails = paymentDetailService.findAll();
		
		return paymentDetails;	
	}

	//@ReadAccess
	@GetMapping(value = "/{paymentDetailId}")
	@ResponseBody
	public PaymentDetailDTO getPaymentDetail(@PathVariable Integer paymentDetailId) {
		
		return (paymentDetailService.getPaymentDetailDTOById(paymentDetailId));
	}

 	//@WriteAccess
 	@RequestMapping(value = "/addPaymentDetail", method = RequestMethod.POST)
	public ResponseEntity<?> addPaymentDetail(@RequestBody PaymentDetailDTO paymentDetailDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = paymentDetailService.addPaymentDetail(paymentDetailDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}
		
		return result.asResponseEntity();
	}

	//@ReadAccess
	@GetMapping("/paymentDetails")
	public ResponseEntity<PaymentDetailPageDTO> getPaymentDetails(PaymentDetailSearchDTO paymentDetailSearchDTO) {
 
		return paymentDetailService.getPaymentDetails(paymentDetailSearchDTO);
	}	

 	//@WriteAccess
	@RequestMapping(value = "/updatePaymentDetail", method = RequestMethod.POST)
	public ResponseEntity<?> updatePaymentDetail(@RequestBody PaymentDetailDTO paymentDetailDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = paymentDetailService.updatePaymentDetail(paymentDetailDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
