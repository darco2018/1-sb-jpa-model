package com.ust.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

// When using JUnit 4, this annotation should be used in combination with @RunWith(SpringRunner.class).
// If you are looking to load your full application configuration, but use an embedded database, you should consider
//       @SpringBootTest combined with @AutoConfigureTestDatabase rather than this annotation.
// @ExtendWith({SpringExtension.class})  @AutoConfigureTestEntityManager
@DataJpaTest // main, controllers services, CLRunner excluded
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void setUp() {
        // given
        User user = new User("darek@zoho.com", "darek987", "ADMIN", "darek");
        testEntityManager.persist(user);
    }

    @Test
    public void savesUserCorrectly() {
        long originalCount = userRepository.count();

        userRepository.save(new User("mike@zoho.com", "mike470", "USER", "mikesoprano"));

        Assertions.assertEquals(originalCount + 1L, userRepository.count());
    }

    @Test
    public void whenFindByUsername_thenReturnUser() {
        // when
        User user = userRepository.findByUsername("darek").get();

        // then
        org.assertj.core.api.Assertions.assertThat(user.getEmail()).isEqualTo("darek@zoho.com");
    }

    @Test
    public void whenFindAll_thenReturnUserList() {
        // when
        List<User> users = userRepository.findAll(); // loads all already loaded from file

        // then
        Assertions.assertEquals(6 + 1, users.size());
        org.assertj.core.api.Assertions.assertThat(users.size()).isEqualTo(6 + 1);
    }
}
