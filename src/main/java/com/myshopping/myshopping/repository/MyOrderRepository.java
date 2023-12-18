package com.myshopping.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.myshopping.modal.MyOrder;

public interface MyOrderRepository extends MongoRepository<MyOrder, String>{

}
