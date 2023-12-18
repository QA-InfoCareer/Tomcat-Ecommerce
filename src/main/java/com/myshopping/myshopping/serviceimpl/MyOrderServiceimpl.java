package com.myshopping.myshopping.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.Windows.Bound;
import com.myshopping.myshopping.dto.CartDto;
import com.myshopping.myshopping.dto.MyOrderDto;
import com.myshopping.myshopping.modal.Bill;
import com.myshopping.myshopping.modal.Cart;
import com.myshopping.myshopping.modal.MyOrder;
import com.myshopping.myshopping.repository.BillRepository;
import com.myshopping.myshopping.repository.CartRepository;
import com.myshopping.myshopping.repository.MyOrderRepository;
import com.myshopping.myshoppingservice.MyOrderService;

@Service
public class MyOrderServiceimpl implements MyOrderService{

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	MyOrderRepository myOrderRepository;

	@Autowired
	BillRepository billRepository;
	@Override
	public List<CartDto> showAllCartProducts() {
		List<Cart> carts=cartRepository.findAll();
		List<CartDto> cartDtos=new ArrayList<CartDto>();
		for(Cart cartItr:carts)
		{
			MyOrder myOrder=new MyOrder();
			BeanUtils.copyProperties(cartItr, myOrder);
			
			CartDto cartDto=new CartDto();
			BeanUtils.copyProperties(cartItr, cartDto);
			cartDtos.add(cartDto);
		}
		return cartDtos;
	}

	@Override
	public List<CartDto> moveAllCartProductsToMyOrder() {
		List<Cart> carts=cartRepository.findAll();
		List<CartDto> cartDto=new ArrayList<>();
		Bill bill=new Bill();
		double totalRate=0;
		double gst=0;
		double totalGst=0;
		double rate=0;
		double deleveryCharge=0;
		List<MyOrder> myOrders=new ArrayList<>();
		for(Cart cartItr:carts)
		{
			/*MyOrder myOrder=new MyOrder();
			BeanUtils.copyProperties(cartItr, myOrder);
			myOrderRepository.save(myOrder);*/
			rate=cartItr.getRate();
			totalRate+=rate;
			gst=(rate*28)/100;
			totalGst+=gst;
			CartDto cartDtos=new CartDto();
			BeanUtils.copyProperties(cartItr, cartDtos);
			cartDto.add(cartDtos);
		}		
		deleveryCharge=((totalGst+totalRate)*2)/100;		
		bill.setTotalPrice(totalRate+totalGst+deleveryCharge);
		bill.setGst(totalGst);
		bill.setDeleveryCharge(deleveryCharge);
		bill.setProductPrice(totalRate);
		billRepository.save(bill);		
		return cartDto;
	}

	@Override
	public List<MyOrder> showAllMyorders(String id) {
		List<MyOrder> myOrders=myOrderRepository.findAll();
		List<MyOrder> myOrders1=new ArrayList<>();
		for(MyOrder myOrder:myOrders)
		{			
			if(myOrder.getUser().getId().equals(id))
			{
				MyOrder myOrders2=new MyOrder();
				BeanUtils.copyProperties(myOrder, myOrders2);
				myOrders1.add(myOrders2);
			}
		}
		return myOrders1;
	}
	
}
