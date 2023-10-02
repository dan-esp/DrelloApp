package com.drello.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.drello.exceptions.EntityNotFoundException;
import com.drello.model.UserEntity;
import com.drello.repository.UserRepository;
import com.drello.service.IUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, UserEntity.class.getName()));
    }

    @Override
    public UserEntity updateEntity(String userId, UserEntity newUserData) {
        UserEntity userToUpdate = findById(userId);

        userToUpdate.setUsername(newUserData.getUsername());
        userToUpdate.setProfileUrl(newUserData.getProfileUrl());
        userToUpdate.setEmail(newUserData.getEmail());
        userToUpdate.setPassword(newUserData.getPassword());

        return userRepository.save(userToUpdate);
    }

    @Override
    public UserEntity delete(String id) {
        UserEntity findResponse = findById(id);
        userRepository.deleteById(id);
        return findResponse;
    }

    @Override
    public boolean addBoard(String userId, String boardId) {
        Query query = new Query(Criteria.where("_id").is(userId));
        Update update = new Update().push("boards", boardId);

        mongoTemplate.updateFirst(query, update, UserEntity.class);
        
        return true;
    }

}
