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
import com.ebayfff.dao.WishListDAO;
import com.ebayfff.domain.WishList;
import com.ebayfff.dto.WishListDTO;
import com.ebayfff.dto.WishListSearchDTO;
import com.ebayfff.dto.WishListPageDTO;
import com.ebayfff.dto.WishListConvertCriteriaDTO;
import com.ebayfff.dto.common.RequestDTO;
import com.ebayfff.dto.common.ResultDTO;
import com.ebayfff.service.WishListService;
import com.ebayfff.util.ControllerUtils;





@Service
public class WishListServiceImpl extends GenericServiceImpl<WishList, Integer> implements WishListService {

    private final static Logger logger = LoggerFactory.getLogger(WishListServiceImpl.class);

	@Autowired
	WishListDAO wishListDao;

	


	@Override
	public GenericDAO<WishList, Integer> getDAO() {
		return (GenericDAO<WishList, Integer>) wishListDao;
	}
	
	public List<WishList> findAll () {
		List<WishList> wishLists = wishListDao.findAll();
		
		return wishLists;	
		
	}

	public ResultDTO addWishList(WishListDTO wishListDTO, RequestDTO requestDTO) {

		WishList wishList = new WishList();

		wishList.setWishListId(wishListDTO.getWishListId());


		wishList.setCreationDate(wishListDTO.getCreationDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		wishList = wishListDao.save(wishList);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<WishList> getAllWishLists(Pageable pageable) {
		return wishListDao.findAll(pageable);
	}

	public Page<WishList> getAllWishLists(Specification<WishList> spec, Pageable pageable) {
		return wishListDao.findAll(spec, pageable);
	}

	public ResponseEntity<WishListPageDTO> getWishLists(WishListSearchDTO wishListSearchDTO) {
	
			Integer wishListId = wishListSearchDTO.getWishListId(); 
   			String sortBy = wishListSearchDTO.getSortBy();
			String sortOrder = wishListSearchDTO.getSortOrder();
			String searchQuery = wishListSearchDTO.getSearchQuery();
			Integer page = wishListSearchDTO.getPage();
			Integer size = wishListSearchDTO.getSize();

	        Specification<WishList> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, wishListId, "wishListId"); 
			
 			

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

		Page<WishList> wishLists = this.getAllWishLists(spec, pageable);
		
		//System.out.println(String.valueOf(wishLists.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(wishLists.getTotalPages()));
		
		List<WishList> wishListsList = wishLists.getContent();
		
		WishListConvertCriteriaDTO convertCriteria = new WishListConvertCriteriaDTO();
		List<WishListDTO> wishListDTOs = this.convertWishListsToWishListDTOs(wishListsList,convertCriteria);
		
		WishListPageDTO wishListPageDTO = new WishListPageDTO();
		wishListPageDTO.setWishLists(wishListDTOs);
		wishListPageDTO.setTotalElements(wishLists.getTotalElements());
		return ResponseEntity.ok(wishListPageDTO);
	}

	public List<WishListDTO> convertWishListsToWishListDTOs(List<WishList> wishLists, WishListConvertCriteriaDTO convertCriteria) {
		
		List<WishListDTO> wishListDTOs = new ArrayList<WishListDTO>();
		
		for (WishList wishList : wishLists) {
			wishListDTOs.add(convertWishListToWishListDTO(wishList,convertCriteria));
		}
		
		return wishListDTOs;

	}
	
	public WishListDTO convertWishListToWishListDTO(WishList wishList, WishListConvertCriteriaDTO convertCriteria) {
		
		WishListDTO wishListDTO = new WishListDTO();
		
		wishListDTO.setWishListId(wishList.getWishListId());

	
		wishListDTO.setCreationDate(wishList.getCreationDate());

	

		
		return wishListDTO;
	}

	public ResultDTO updateWishList(WishListDTO wishListDTO, RequestDTO requestDTO) {
		
		WishList wishList = wishListDao.getById(wishListDTO.getWishListId());

		wishList.setWishListId(ControllerUtils.setValue(wishList.getWishListId(), wishListDTO.getWishListId()));

		wishList.setCreationDate(ControllerUtils.setValue(wishList.getCreationDate(), wishListDTO.getCreationDate()));



        wishList = wishListDao.save(wishList);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public WishListDTO getWishListDTOById(Integer wishListId) {
	
		WishList wishList = wishListDao.getById(wishListId);
			
		
		WishListConvertCriteriaDTO convertCriteria = new WishListConvertCriteriaDTO();
		return(this.convertWishListToWishListDTO(wishList,convertCriteria));
	}







}
