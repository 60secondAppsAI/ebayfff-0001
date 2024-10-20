package com.ebayfff.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.ebayfff.dao.GenericDAO;
import com.ebayfff.service.GenericService;
import com.ebayfff.service.impl.GenericServiceImpl;
import com.ebayfff.dao.WishListItemDAO;
import com.ebayfff.domain.WishListItem;
import com.ebayfff.dto.WishListItemDTO;
import com.ebayfff.dto.WishListItemSearchDTO;
import com.ebayfff.dto.WishListItemPageDTO;
import com.ebayfff.dto.WishListItemConvertCriteriaDTO;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;
import com.ebayfff.service.WishListItemService;
import com.ebayfff.util.ControllerUtils;





@Service
public class WishListItemServiceImpl extends GenericServiceImpl<WishListItem, Integer> implements WishListItemService {

    private final static Logger logger = LoggerFactory.getLogger(WishListItemServiceImpl.class);

	@Autowired
	WishListItemDAO wishListItemDao;

	


	@Override
	public GenericDAO<WishListItem, Integer> getDAO() {
		return (GenericDAO<WishListItem, Integer>) wishListItemDao;
	}
	
	public List<WishListItem> findAll () {
		List<WishListItem> wishListItems = wishListItemDao.findAll();
		
		return wishListItems;	
		
	}

	public ResultDTO addWishListItem(WishListItemDTO wishListItemDTO, RequestDTO requestDTO) {

		WishListItem wishListItem = new WishListItem();

		wishListItem.setWishListItemId(wishListItemDTO.getWishListItemId());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		wishListItem = wishListItemDao.save(wishListItem);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<WishListItem> getAllWishListItems(Pageable pageable) {
		return wishListItemDao.findAll(pageable);
	}

	public Page<WishListItem> getAllWishListItems(Specification<WishListItem> spec, Pageable pageable) {
		return wishListItemDao.findAll(spec, pageable);
	}

	public ResponseEntity<WishListItemPageDTO> getWishListItems(WishListItemSearchDTO wishListItemSearchDTO) {
	
			Integer wishListItemId = wishListItemSearchDTO.getWishListItemId(); 
 			String sortBy = wishListItemSearchDTO.getSortBy();
			String sortOrder = wishListItemSearchDTO.getSortOrder();
			String searchQuery = wishListItemSearchDTO.getSearchQuery();
			Integer page = wishListItemSearchDTO.getPage();
			Integer size = wishListItemSearchDTO.getSize();

	        Specification<WishListItem> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, wishListItemId, "wishListItemId"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<WishListItem> wishListItems = this.getAllWishListItems(spec, pageable);
		
		//System.out.println(String.valueOf(wishListItems.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(wishListItems.getTotalPages()));
		
		List<WishListItem> wishListItemsList = wishListItems.getContent();
		
		WishListItemConvertCriteriaDTO convertCriteria = new WishListItemConvertCriteriaDTO();
		List<WishListItemDTO> wishListItemDTOs = this.convertWishListItemsToWishListItemDTOs(wishListItemsList,convertCriteria);
		
		WishListItemPageDTO wishListItemPageDTO = new WishListItemPageDTO();
		wishListItemPageDTO.setWishListItems(wishListItemDTOs);
		wishListItemPageDTO.setTotalElements(wishListItems.getTotalElements());
		return ResponseEntity.ok(wishListItemPageDTO);
	}

	public List<WishListItemDTO> convertWishListItemsToWishListItemDTOs(List<WishListItem> wishListItems, WishListItemConvertCriteriaDTO convertCriteria) {
		
		List<WishListItemDTO> wishListItemDTOs = new ArrayList<WishListItemDTO>();
		
		for (WishListItem wishListItem : wishListItems) {
			wishListItemDTOs.add(convertWishListItemToWishListItemDTO(wishListItem,convertCriteria));
		}
		
		return wishListItemDTOs;

	}
	
	public WishListItemDTO convertWishListItemToWishListItemDTO(WishListItem wishListItem, WishListItemConvertCriteriaDTO convertCriteria) {
		
		WishListItemDTO wishListItemDTO = new WishListItemDTO();
		
		wishListItemDTO.setWishListItemId(wishListItem.getWishListItemId());

	

		
		return wishListItemDTO;
	}

	public ResultDTO updateWishListItem(WishListItemDTO wishListItemDTO, RequestDTO requestDTO) {
		
		WishListItem wishListItem = wishListItemDao.getById(wishListItemDTO.getWishListItemId());

		wishListItem.setWishListItemId(ControllerUtils.setValue(wishListItem.getWishListItemId(), wishListItemDTO.getWishListItemId()));



        wishListItem = wishListItemDao.save(wishListItem);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WishListItemDTO getWishListItemDTOById(Integer wishListItemId) {
	
		WishListItem wishListItem = wishListItemDao.getById(wishListItemId);
			
		
		WishListItemConvertCriteriaDTO convertCriteria = new WishListItemConvertCriteriaDTO();
		return(this.convertWishListItemToWishListItemDTO(wishListItem,convertCriteria));
	}







}
