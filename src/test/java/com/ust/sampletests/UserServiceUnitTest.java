package com.ust.sampletests;

import com.ust.user.User;
import com.ust.user.UserRepository;
import com.ust.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

// junit4 @RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // a new test instance will be created once per test class
public class UserServiceUnitTest {

    @Mock // used with MockitoJUnitRunner;    Similar to @MockBean + SpringRunner
    private UserRepository userRepository;

    private UserService userService;

    @BeforeAll // @Before  not working with JUIT5
    public  void setUp111() throws Exception{

        System.out.println(">>>>>>>>> @BeforeAll <<<<<<<<<<<<<<<");
        System.out.println(userRepository);
        System.out.println(userService);

    }
/*
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;*/

    @Before
    public  void init() {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository); // doesnt work when in @BeforeAll -> runs before class instance created?
        System.out.println("------------> @BeforeEach <------------------------------------------");
        System.out.println(">>>" + userRepository);
        System.out.println(userService);
        //userService= new UserService(userRepository);

        User user = new User("darek@zoho.com", "darek987", "ADMIN", "darek");

        org.mockito.Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));
    }

 /*   Unnecessary stubbings detected. Instead of mocking userService.getUsers() we try to use the ACTUAL method implementation
 @Test
    public void whenTestStarts_thenHibernateOrCLRunnerDoesntCreateInitialRecords() {
        List<User> users = userService.getUsers();
        org.junit.jupiter.api.Assertions.assertEquals(0, users.size());
    }*/

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        //arrange
        String username = "darek";

        System.out.println(userService);

        //act
        Optional<User> found = userService.findUserByUsername(username);

        // assert
        Assertions.assertThat(found.get().getUsername())
                .isEqualTo(username);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound_2() {
        //arrange
        String username = "darek";

        System.out.println(userService);

        //act
        Optional<User> found = userService.findUserByUsername(username);

        // assert
        Assertions.assertThat(found.get().getUsername())
                .isEqualTo(username);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound_3() {
        //arrange
        String username = "darek";

        System.out.println(userService);

        //act
        Optional<User> found = userService.findUserByUsername(username);

        // assert
        Assertions.assertThat(found.get().getUsername())
                .isEqualTo(username);
    }
}
