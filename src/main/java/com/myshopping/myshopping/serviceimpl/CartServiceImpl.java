package com.myshopping.myshopping.serviceimpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;



import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.myshopping.myshopping.dto.CartDto;
import com.myshopping.myshopping.modal.Cart;
import com.myshopping.myshopping.repository.CartRepository;
import com.myshopping.myshoppingservice.CartService;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public List<CartDto> showAllCartProducts() {
		List<Cart> cart=cartRepository.findAll();
		List<CartDto> cartDto=new ArrayList<CartDto>();
		for(Cart cartItr: cart)
		{
			CartDto cartDtoProduct=new CartDto();
			cartDtoProduct.setId(cartItr.getId());
			cartDtoProduct.setName(cartItr.getName());
			cartDtoProduct.setRate(cartItr.getRate());
			cartDto.add(cartDtoProduct);
		}
		return cartDto;
	}

	@Override
	public Boolean deletePerticularProduct(String productid) {
		Optional<Cart> cart=cartRepository.findById(productid);
		if(cart!=null)
		{
			cartRepository.deleteById(productid);
			return true;
		}else
		{
			return false;
		}				
	}

	@Override
	public Boolean deleteAllCartProduct() {
		
		cartRepository.deleteAll();
		return true;		
	}
	
	@Override
	public List<CartDto> findProductByUserId(String userId) {
	
    List<Cart> products=cartRepository.findAll();
	List<CartDto> cartDtos=new ArrayList<>();
    for(Cart cart:products)
	{
		if(cart.getUser().getId()==userId)
		{
			CartDto cartDto=new CartDto();
			BeanUtils.copyProperties(cart, cartDto);
			cartDtos.add(cartDto);
			System.out.println(cartDto.getUser().getName());
		}
	}

	return cartDtos;
	}

}
