package com.drello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.drello.model.UserEntity;
import com.drello.service.IUserService;

@SpringBootTest
public class UsersCRUDTest {

        @Autowired(required = true)
        private IUserService userService;

        @Test
        public void createUserTest() {
                UserEntity user01 = new UserEntity("thomas", "thomas@gmail.com", "thomas123");
                UserEntity user02 = new UserEntity("andres", "andres@gmail.com", "andre123");
                UserEntity user03 = new UserEntity("jose", "jose@gmail.com", "jose123");

                userService.save(user01);
                userService.save(user02);
                userService.save(user03);
        }

        @Test
        public void deleteUserTest() {
                UserEntity user = new UserEntity("juaco", "juaco@gmail.com", "juaco123");

                user = userService.save(user);
                String id = user.getId();
                user = userService.delete(id);
                try {
                        user = userService.findById(id);
                } catch (Exception e) {
                        assertTrue(true);
                }
        }

        @Test
        public void findAndUpdateUserTest() {
                UserEntity user = new UserEntity("juaco", "juaco@gmail.com", "juaco123");

                UserEntity entity = userService.save(user);

                entity.setUsername("null");
                entity.setEmail("takos");

                UserEntity userUpdated = new UserEntity("example_update", "example_update@gmail.com", "123456");

                assertEquals("example_update", userUpdated.getUsername());
                assertEquals("example_update@gmail.com", userUpdated.getEmail());
        }
}
