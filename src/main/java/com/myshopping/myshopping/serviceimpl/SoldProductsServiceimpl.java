package com.myshopping.myshopping.serviceimpl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.LocalTaskExecutorThreadPool;
import org.springframework.stereotype.Service;

import com.myshopping.myshopping.dto.SoldProductsDto;
import com.myshopping.myshopping.modal.Bill;
import com.myshopping.myshopping.modal.BillCopy;
import com.myshopping.myshopping.modal.Cart;
import com.myshopping.myshopping.modal.MyOrder;
import com.myshopping.myshopping.modal.SoldProducts;
import com.myshopping.myshopping.modal.User;
import com.myshopping.myshopping.repository.BillCopyRepository;
import com.myshopping.myshopping.repository.BillRepository;
import com.myshopping.myshopping.repository.CartRepository;
import com.myshopping.myshopping.repository.MyOrderRepository;
import com.myshopping.myshopping.repository.SoldProductsRepository;
import com.myshopping.myshopping.repository.UserRepository;
import com.myshopping.myshoppingservice.SoldProductsService;
@Service
public class SoldProductsServiceimpl implements SoldProductsService{

	@Autowired
	SoldProductsRepository soldProductsRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	MyOrderRepository myOrderRepository;
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	BillCopyRepository billCopyRepository;
	
	@Autowired
	UserRepository userRepository;
	@Override
	public Boolean addToSoldProductTable(String id) {
		List<Cart> carts=cartRepository.findAll();
		List<Bill> bills= billRepository.findAll();
		Optional<User> user=userRepository.findById(id);
		BillCopy billCopy=new BillCopy();
		for(Bill billItr:bills)
		{			
			billCopy.setId(billItr.getId());
			billCopy.setDeleveryCharge(billItr.getDeleveryCharge());
			billCopy.setGst(billItr.getGst());
			billCopy.setProductPrice(billItr.getProductPrice());
			billCopy.setTotalPrice(billItr.getTotalPrice());
			billCopyRepository.save(billCopy);
		}
		
		for(Cart cartItr:carts)
		{
			MyOrder myOrder=new MyOrder();
			SoldProducts soldProducts=new SoldProducts();
			BeanUtils.copyProperties(cartItr, myOrder);
			BeanUtils.copyProperties(cartItr, soldProducts);
			User user1=user.get();			
			myOrder.setUser(user1);
			soldProducts.setBillCopy(billCopy);
			soldProducts.setUser(user1);
			Date date=new Date();
			LocalTime localTime=LocalTime.now();
			soldProducts.setDate(date);
			soldProducts.setLocalTime(localTime);
			myOrderRepository.save(myOrder);
			soldProductsRepository.save(soldProducts);
		}
		List<SoldProducts> soldProducts=soldProductsRepository.findAll();
		if(soldProducts!=null)
			return true;
		else
			return false;		
	}
	@Override
	public List<SoldProductsDto> showAllSoldProducts() {
		List<SoldProducts> soldProducts=soldProductsRepository.findAll();
		List<SoldProductsDto> soldProductsDtos=new ArrayList<>();
		for(SoldProducts soldProducts2:soldProducts)
		{
			SoldProductsDto dto=new SoldProductsDto();
			BeanUtils.copyProperties(soldProducts2, dto);
			soldProductsDtos.add(dto);
		}
		return soldProductsDtos;
	}
	
}
