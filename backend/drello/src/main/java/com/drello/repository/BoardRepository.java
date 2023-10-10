package com.drello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.drello.model.Board;

@Repository
public interface BoardRepository extends MongoRepository<Board, String> {

}
