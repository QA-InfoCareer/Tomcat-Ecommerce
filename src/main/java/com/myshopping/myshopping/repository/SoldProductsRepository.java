package com.myshopping.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.myshopping.modal.SoldProducts;

public interface SoldProductsRepository extends MongoRepository<SoldProducts, String>{

}
