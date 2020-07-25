package com.ust.sampletests;

import com.ust.user.User;
import com.ust.user.UserRepository;
import com.ust.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserSeviceUnitTest_1 {

    @Mock // used with MockitoJUnitRunner;    Similar to @MockBean + SpringRunner
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository); // doesnt work when in @BeforeAll -> runs before class instance created?

        User user = new User("darek@zoho.com", "darek987", "ADMIN", "darek");

        org.mockito.Mockito.when(userRepository.findByUsername(user.getUsername()))
                .thenReturn(java.util.Optional.of(user));
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        //arrange
        String username = "darek";

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

        //act
        Optional<User> found = userService.getUserByUsername(username);

        // assert
        Assertions.assertThat(found.get().getUsername())
                .isEqualTo(username);
    }
}

