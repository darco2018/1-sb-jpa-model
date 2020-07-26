package com.ust.sampletests;

import com.ust.user.User;
import com.ust.user.UserRepository;
import com.ust.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

// No MockitoExtension/MockitoJUnitRunner, so we have to init mocks in @BeforeAll
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // makes possible to have @BeforeAll non-static
// a new test instance will be created once per test class (not  defaultPER_METHOD )
public class UserServiceTest_3 {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    public void setUp() {
        System.out.println("------------> @BeforeEach <------------------------------------------");
        System.out.println(">>>" + userRepository);
        System.out.println(userService);

        User user = new User("darek@zoho.com", "darek987", "ADMIN", "darek");

        org.mockito.Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        //arrange
        String username = "darek";

        System.out.println(userService);

        //act
        Optional<User> found = userService.getUserByUsername(username);

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
        Optional<User> found = userService.getUserByUsername(username);

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
        Optional<User> found = userService.getUserByUsername(username);

        // assert
        Assertions.assertThat(found.get().getUsername())
                .isEqualTo(username);
    }
}


