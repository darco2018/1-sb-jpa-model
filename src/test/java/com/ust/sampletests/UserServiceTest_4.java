package com.ust.sampletests;

import com.ust.user.User;
import com.ust.user.UserRepository;
import com.ust.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class UserServiceTest_4 {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService = new UserService(userRepository);

    @BeforeEach
    public void setUp() {

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
