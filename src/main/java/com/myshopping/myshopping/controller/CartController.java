package com.myshopping.myshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myshopping.myshopping.dto.CartDto;
import com.myshopping.myshopping.modal.Cart;
import com.myshopping.myshoppingservice.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService cartService;
	
	@RequestMapping(path="/showallproductcart",method = RequestMethod.GET)
	public List<CartDto> showAllCartProducts() {
		return cartService.showAllCartProducts();
	}
	@RequestMapping(path = "/removeproduct",method = RequestMethod.POST)
	public Boolean deletePerticularProduct(String productid) {
		return cartService.deletePerticularProduct(productid);
	}
	@RequestMapping(path = "/deleteallcart",method = RequestMethod.POST)
	public Boolean deleteAllCartProduct() {
		return cartService.deleteAllCartProduct();
	}
	//fetch data from cart based on userid
	@RequestMapping(path = "/findproductbyuserid",method = RequestMethod.POST)
	public List<CartDto> findProductByUserId(String userId) {
		return cartService.findProductByUserId(userId);
	}
}
