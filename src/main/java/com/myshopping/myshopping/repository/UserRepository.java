package com.myshopping.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.myshopping.modal.User;

public interface UserRepository extends MongoRepository<User,String>{

}
