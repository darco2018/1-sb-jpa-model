package com.ust.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenAppStarts_thenHibernateCreatesInitialRecords() {
        List<User> users = userService.getUsers();
        Assertions.assertEquals(3, users.size());
    }
}
