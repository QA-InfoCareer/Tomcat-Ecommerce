package com.myshopping.myshoppingservice;

import com.myshopping.myshopping.dto.BillDto;

public interface BillService {
	Boolean saveBillCopy();
	BillDto showCurrentBill();
	Boolean deleteBillCopy();
}
