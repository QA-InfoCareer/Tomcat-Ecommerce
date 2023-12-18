package com.myshopping.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.myshopping.modal.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
