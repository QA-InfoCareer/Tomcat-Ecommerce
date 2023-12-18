package com.myshopping.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.myshopping.modal.Cart;

public interface CartRepository extends MongoRepository<Cart, String>{

}
