package com.drello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.drello.model.UserEntity;
import com.drello.service.IUserService;
import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRestController {

    @Autowired(required = true)
    private IUserService userService;

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestParam UserEntity userEntity) {
        userService.save(userEntity);
        return ResponseEntity.ok("Successfully operation");
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable String id) {
        UserEntity userEntity = userService.findById(id);
        return userEntity;
    }

    @PutMapping("/{id}")
    public UserEntity updateUserData(@PathVariable String id, @RequestBody UserEntity userEntity) {
        return userService.updateEntity(id, userEntity);
    }

    @DeleteMapping("/{id}")
    public UserEntity deleteUserData(@PathVariable String id) {
        return userService.delete(id);
    }


     @GetMapping("/search/{partial}")
    public List<UserEntity> getMatchUsers(@PathVariable String partial) {
        List<UserEntity> users = userService.getUsersWithPartialUsernameOrEmail(partial);
        System.out.println(users);
        return users;
    }

}
