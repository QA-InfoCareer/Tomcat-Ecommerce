package com.myshopping.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.myshopping.modal.Bill;

public interface BillRepository  extends MongoRepository<Bill, String>{

}
