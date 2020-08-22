package com.ust.sampletests;

import com.ust.user.User;
import com.ust.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

@DataJpaTest // main, @Component(controllers services, CLRunner)  excluded
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach // runs between each method
    public void setUp() {
        // given
        User user = new User("darek@zoho.com", "darek987", "ADMIN", "darek");
        testEntityManager.persistAndFlush(user);

        //    userRepository.save() would just save it to the cache & findByName would get it from the cashe
        User savedUser = testEntityManager.persistFlushFind(new User("zorro@zoho.com", "zorro123", "USER", "zorro"));

    }

    @Test
    public void savesUserCorrectly() {
        long originalCount = userRepository.count();

        userRepository.save(new User("mike@zoho.com", "atleasteight", "USER", "mikesoprano"));

        Assertions.assertEquals(originalCount + 1L, userRepository.count());
    }

    @Test
    public void savesUserCorrectly_includingDataFromFileAndCLRunner() {

        userRepository.save(new User("mike@zoho.com", "mike470eight", "USER", "mikesoprano"));

        Assertions.assertEquals(6 + 2 + 1, userRepository.count());
    }

    @Test
    public void whenFindByUsernameAddedByLRunner_thenReturnUser() {
        // when
        Optional<User> userOpt = userRepository.findByUsername("CLuser");

        // then
        org.assertj.core.api.Assertions.assertThat(userOpt.isPresent()).isFalse();
    }

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // when
        User user = userRepository.findByUsername("darek").get();

        // then
        org.assertj.core.api.Assertions.assertThat(user.getEmail()).isEqualTo("darek@zoho.com");
    }

    @Test
    public void whenFindAll_thenReturnUserList_includingDataFromFile_excludongCLRunner() {
        // when
        List<User> users = userRepository.findAll(); // loads all already loaded from file

        // then
        Assertions.assertEquals(6 + 2, users.size());
        org.assertj.core.api.Assertions.assertThat(users.size()).isEqualTo(6 + 2);
    }
}
