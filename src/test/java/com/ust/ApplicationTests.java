package com.ust;

import com.ust.user.User;
import com.ust.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void whenAppStarts_thenHibernateCreatesInitialRecords() {
        List<User> users = userService.getUsers();
        Assertions.assertEquals(3, users.size());
    }
}
