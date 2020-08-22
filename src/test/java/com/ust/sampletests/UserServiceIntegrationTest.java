package com.ust.sampletests;

import com.ust.user.User;
import com.ust.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void whenAppStarts_thenHibernateCreatesInitialRecords() {
        List<User> users = userService.findAllUsers();
        Assertions.assertEquals(6+1, users.size());
    }

    //---------- Custom SpringData queries using method names ------------------

  /*  @Test
    public void getsAllUsersByUsername() {
        List<User> users = userService.findAllUsersByUsername("John Brown");
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("John Brown", users.get(0).getUsername());
    }*/

    @Test
    public void getsAllUsersWhereEmailContainsGivenExpression() {
        List<User> users = userService.findAllUsersByEmailContaining("gmail.com");
        Assertions.assertEquals(3, users.size());
    }

    @Test
    public void getsAllUsersWhereNameLike() {
        List<User> users = userService.findAllUsersWhereUsernameLike("%hn%");
        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void getsAllUsersWhereNameBetweenTwoStrings()  {
        List<User> users = userService.findAllUsersWhereUsernameBetween("A", "D");
        Assertions.assertEquals(3+1, users.size());
    }

    //---------- Custom SpringData queries using native SQL ------------------
    @Test
    public void getsDisctinctNumberOfUserRoles(){
        int noOfRoles = userService.getDisctinctNoOfUserRoles();
        Assertions.assertEquals(2, noOfRoles);
    }

    //---------- Custom SpringData queries using JPQL  ------------------
    @Test
    public void getsUsersWithGivenEmailAndUsername(){
        List<User> users = userService.findUserByEmailAndUsername("john@gmail.com", "John Brown");
        Assertions.assertEquals(1, users.size());
    }
}
