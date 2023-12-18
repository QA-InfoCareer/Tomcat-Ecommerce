package com.myshopping.myshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.myshopping.dto.BillDto;
import com.myshopping.myshoppingservice.BillService;

@RestController
@RequestMapping("/bill")
public class BillControlller {
	@Autowired
	BillService billService;
	@RequestMapping(path="/showbillcopy",method = RequestMethod.POST)
	public BillDto showCurrentBill() {
		return billService.showCurrentBill();
	}
	@RequestMapping(path="/cancleorder",method = RequestMethod.POST)
	public boolean deleteBillCopy() {
		return billService.deleteBillCopy();		
	}
}
