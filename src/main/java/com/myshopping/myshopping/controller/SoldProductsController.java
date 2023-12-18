package com.myshopping.myshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.myshopping.dto.SoldProductsDto;
import com.myshopping.myshoppingservice.SoldProductsService;

@RestController
@RequestMapping("/soldproduct")
public class SoldProductsController {
	@Autowired
	SoldProductsService soldProductsService;
	
	@RequestMapping(path = "/ordercopytosoldproduct",method = RequestMethod.POST)
	public Boolean addToSoldProductTable(String id) {
		return soldProductsService.addToSoldProductTable(id);
	}
	@RequestMapping(path = "/showallsoldproducts",method = RequestMethod.GET)
	public List<SoldProductsDto> showAllSoldProducts() {
		return soldProductsService.showAllSoldProducts();
	}

}
