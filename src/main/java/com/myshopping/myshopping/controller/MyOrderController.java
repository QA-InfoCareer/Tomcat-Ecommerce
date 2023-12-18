package com.myshopping.myshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.myshopping.dto.CartDto;
import com.myshopping.myshopping.dto.MyOrderDto;
import com.myshopping.myshopping.modal.MyOrder;
import com.myshopping.myshoppingservice.MyOrderService;

@RestController
@RequestMapping("/myorders")
public class MyOrderController {
	@Autowired
	MyOrderService myOrderService;
	@RequestMapping(path="/myorderdetails",method = RequestMethod.POST)
	public List<CartDto> showAllCartProducts() {
		return myOrderService.showAllCartProducts();
	}
	@RequestMapping(path="/orderplaced",method = RequestMethod.POST)
	public List<CartDto> moveAllCartProductsToMyOrder() {
		return myOrderService.moveAllCartProductsToMyOrder();
	}
	@RequestMapping(path="/myorder",method = RequestMethod.POST)
	public List<MyOrder> showAllMyorders(String id) {
		return myOrderService.showAllMyorders(id);
	}
}
