package com.myshopping.myshopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myshopping.myshopping.modal.BillCopy;

public interface BillCopyRepository extends MongoRepository<BillCopy, String>{

}
