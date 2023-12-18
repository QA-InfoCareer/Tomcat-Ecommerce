package com.myshopping.myshoppingservice;

import java.util.List;

import com.myshopping.myshopping.dto.CartDto;
import com.myshopping.myshopping.modal.Cart;

public interface CartService {
	List<CartDto> showAllCartProducts();
	Boolean deletePerticularProduct(String productid);
	Boolean deleteAllCartProduct();
	List<CartDto> findProductByUserId(String userId);
}
