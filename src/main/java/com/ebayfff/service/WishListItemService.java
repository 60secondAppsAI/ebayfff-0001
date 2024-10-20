package com.ebayfff.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.ebayfff.domain.WishListItem;
import com.ebayfff.dto.WishListItemDTO;
import com.ebayfff.dto.WishListItemSearchDTO;
import com.ebayfff.dto.WishListItemPageDTO;
import com.ebayfff.dto.WishListItemConvertCriteriaDTO;
import com.ebayfff.service.GenericService;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface WishListItemService extends GenericService<WishListItem, Integer> {

	List<WishListItem> findAll();

	ResultDTO addWishListItem(WishListItemDTO wishListItemDTO, RequestDTO requestDTO);

	ResultDTO updateWishListItem(WishListItemDTO wishListItemDTO, RequestDTO requestDTO);

    Page<WishListItem> getAllWishListItems(Pageable pageable);

    Page<WishListItem> getAllWishListItems(Specification<WishListItem> spec, Pageable pageable);

	ResponseEntity<WishListItemPageDTO> getWishListItems(WishListItemSearchDTO wishListItemSearchDTO);
	
	List<WishListItemDTO> convertWishListItemsToWishListItemDTOs(List<WishListItem> wishListItems, WishListItemConvertCriteriaDTO convertCriteria);

	WishListItemDTO getWishListItemDTOById(Integer wishListItemId);







}





