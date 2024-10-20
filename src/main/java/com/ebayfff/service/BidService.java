package com.ebayfff.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ebayfff.domain.Bid;
import com.ebayfff.dto.BidDTO;
import com.ebayfff.dto.BidSearchDTO;
import com.ebayfff.dto.BidPageDTO;
import com.ebayfff.dto.BidConvertCriteriaDTO;
import com.ebayfff.service.GenericService;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BidService extends GenericService<Bid, Integer> {

	List<Bid> findAll();

	ResultDTO addBid(BidDTO bidDTO, RequestDTO requestDTO);

	ResultDTO updateBid(BidDTO bidDTO, RequestDTO requestDTO);

    Page<Bid> getAllBids(Pageable pageable);

    Page<Bid> getAllBids(Specification<Bid> spec, Pageable pageable);

	ResponseEntity<BidPageDTO> getBids(BidSearchDTO bidSearchDTO);
	
	List<BidDTO> convertBidsToBidDTOs(List<Bid> bids, BidConvertCriteriaDTO convertCriteria);

	BidDTO getBidDTOById(Integer bidId);







}





