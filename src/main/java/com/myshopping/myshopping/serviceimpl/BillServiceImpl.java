package com.myshopping.myshopping.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.myshopping.myshopping.dto.BillDto;
import com.myshopping.myshopping.modal.Bill;
import com.myshopping.myshopping.repository.BillRepository;
import com.myshopping.myshoppingservice.BillService;
@Service
public class BillServiceImpl implements BillService{

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Boolean saveBillCopy() {
		
		return null;
	}

	@Override
	public BillDto showCurrentBill() {
		List<Bill> bills=billRepository.findAll();
		BillDto billDto=new BillDto();	
		for(Bill bill:bills)
		{
		billDto.setId(bill.getId());
		billDto.setTotalPrice(bill.getTotalPrice());
		billDto.setGst(bill.getGst());
		billDto.setDeleveryCharge(bill.getDeleveryCharge());
		billDto.setProductPrice(bill.getProductPrice());
		}
		return billDto;
	}

	@Override
	public Boolean deleteBillCopy() {
		billRepository.deleteAll();
		List<Bill> bill=billRepository.findAll();
		if(bill==null)
			return true;
		else
			return false;
		
	}

}
