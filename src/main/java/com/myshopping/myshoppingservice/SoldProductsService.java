package com.myshopping.myshoppingservice;

import java.util.List;

import com.myshopping.myshopping.dto.SoldProductsDto;

public interface SoldProductsService {
	Boolean addToSoldProductTable(String id);
	List<SoldProductsDto> showAllSoldProducts();
}
