package com.ust.sampletests;

import com.ust.user.User;
import com.ust.user.UserRepository;
import com.ust.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest_2 {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

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

