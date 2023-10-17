package com.drello.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.drello.model.UserEntity;

@Service
public interface IUserService {

    UserEntity save(UserEntity userEntity);

    UserEntity findById(String id);

    UserEntity updateEntity(String userId, UserEntity userEntity);

    UserEntity delete(String id);

    public boolean addBoard(String userId, String boardId);

    List<UserEntity> getUsersWithPartialUsernameOrEmail(String partilValue);
}
