package com.ebayfff.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ebayfff.domain.WishList;
import com.ebayfff.dto.WishListDTO;
import com.ebayfff.dto.WishListSearchDTO;
import com.ebayfff.dto.WishListPageDTO;
import com.ebayfff.dto.WishListConvertCriteriaDTO;
import com.ebayfff.service.GenericService;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WishListService extends GenericService<WishList, Integer> {

	List<WishList> findAll();

	ResultDTO addWishList(WishListDTO wishListDTO, RequestDTO requestDTO);

	ResultDTO updateWishList(WishListDTO wishListDTO, RequestDTO requestDTO);

    Page<WishList> getAllWishLists(Pageable pageable);

    Page<WishList> getAllWishLists(Specification<WishList> spec, Pageable pageable);

	ResponseEntity<WishListPageDTO> getWishLists(WishListSearchDTO wishListSearchDTO);
	
	List<WishListDTO> convertWishListsToWishListDTOs(List<WishList> wishLists, WishListConvertCriteriaDTO convertCriteria);

	WishListDTO getWishListDTOById(Integer wishListId);







}





