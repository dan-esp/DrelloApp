package com.drello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.drello.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

}
