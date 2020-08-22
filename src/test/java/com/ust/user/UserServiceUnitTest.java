package com.ust.user;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class UserServiceUnitTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService = new UserService(userRepository);

    //private User user = Mockito.mock(User.class);
    private User USER;
    private static final long USER_ID = 1;

    /**
     * Mock responses to commonly invoked methods.
     */
    @BeforeEach
    public void setupReturnValuesOfMockMethods() {
        //clearRepo();
        USER = new User("zorro@zoho.com", "zorro123", "USER", "zorro");
        //when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(userRepository.findAllByUsername(anyString())).thenReturn(anyList());

     /*   when(tutorialRepository.findById(TUTORIAL_ID)).thenReturn(Optional.of(tutorial));
        when(tutorial.getId()).thenReturn(TUTORIAL_ID);
        when(tRatingRepository.findByTutorialIdAndUserId(TUTORIAL_ID,USER_ID)).thenReturn(Optional.of(tRating));
        when(tRatingRepository.findByTutorialId(TUTORIAL_ID)).thenReturn(Arrays.asList(tRating));*/
    }


    /**************************************************************************************
     *
     * Verify the service's return values
     *
     **************************************************************************************/
    @Disabled
    @Test
    public void test() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER));

        Assertions.assertThat(userService.findUserbyId(USER_ID)).isNotNull();
       // Assertions.assertThat(userService.findUserbyId(USER_ID).getUsername()).isEqualTo("zorro");
    }

   /* @Test
    public void lookupUserById() {

        when(userRepository.findById(USER_ID)).thenReturn(any(Optional.class).of(USER));

        Assertions.assertThat(userService.findUserbyId(USER_ID)).isEqualTo(new User());
    }*/

  /* Test
    public void lookupAll() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(USER));

        assertThat(userService.findAllUsers().get(0), is(USER));
    }*/
}
