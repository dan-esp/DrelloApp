package com.drello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.drello.model.ListCard;

@Repository
public interface ListRepository extends MongoRepository<ListCard,String> {
    
}
