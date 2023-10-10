package com.drello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.drello.model.Card;

@Repository
public interface CardRepository extends MongoRepository<Card,String> {

}
