package com.ust.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void existsNoArgsConstructor() {
        User user = new User();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isNull();
    }

    @Test
    public void existAllArgsConstructorAndGetters() {

        User user = new User(1L, "zorro@gmail.com", "zorro123", "ADMIN", "zorro");
        testGetters(user);
        Assertions.assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    public void existsAllArgsExceptIdConstructorAndGetters() {
        User user = new User("zorro@gmail.com", "zorro123", "ADMIN", "zorro");
        testGetters(user);
        Assertions.assertThat(user.getId()).isNull();
    }

    private void testGetters(User user) {
        Assertions.assertThat(user.getEmail()).isEqualTo("zorro@gmail.com");
        Assertions.assertThat((user.getPassword())).isEqualTo("zorro123");
        Assertions.assertThat(user.getRole()).isEqualTo("ADMIN");
        Assertions.assertThat(user.getUsername()).isEqualTo("zorro");
    }

    @Test
    public void equalsHashcodeVerify() {

        User user1 = new User(1L, "zorro@gmail.com", "zorro123", "ADMIN", "zorro");
        User user2 = new User(1L, "zorro@gmail.com", "zorro123", "ADMIN", "zorro");
        User userDifferent = new User(1L, "XXX@gmail.com", "zorro123", "ADMIN", "zorro");

        Assertions.assertThat(user1).isEqualTo(user2);
        Assertions.assertThat(user1).isNotEqualTo(userDifferent);

        Assertions.assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
        Assertions.assertThat(user1.hashCode()).isNotEqualTo(userDifferent.hashCode());

    }
}
