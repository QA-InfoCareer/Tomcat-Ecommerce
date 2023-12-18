package com.myshopping.myshoppingservice;

import java.util.List;

import com.myshopping.myshopping.dto.CartDto;
import com.myshopping.myshopping.dto.MyOrderDto;
import com.myshopping.myshopping.modal.MyOrder;

public interface MyOrderService {
	List<CartDto> showAllCartProducts();
	List<CartDto> moveAllCartProductsToMyOrder();
	List<MyOrder> showAllMyorders(String id);
}
