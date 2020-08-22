package com.ust.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {


    private static Validator validator;

    @BeforeEach
    public  void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void passwordIsNull() {

        User nullPasswordUser = new User("abc@server2", null, "ADMIN", "zo2");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(nullPasswordUser);
        Set<String> messages = new HashSet<>();
        constraintViolations.forEach(violation -> messages.add(violation.getMessage()));

        assertEquals(2, constraintViolations.size());
        assertTrue(messages.contains("must not be blank"));
        assertTrue(messages.contains("must not be null"));
        //assertEquals("must not be blank", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void passwordIsTooShort() {

        User nullPasswordUser = new User("abc@server2", "123", "ADMIN", "zo2");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(nullPasswordUser);
        Set<String> messages = new HashSet<>();
        constraintViolations.forEach(violation -> messages.add(violation.getMessage()));

        assertEquals(1, constraintViolations.size());
        assertEquals("size must be between 8 and 20", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void emailIsNotValid() {

        User invalidEmailUser = new User("notvalid", "12345678", "ADMIN", "zo2");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(invalidEmailUser);
        Set<String> messages = new HashSet<>();
        constraintViolations.forEach(violation -> messages.add(violation.getMessage()));

        assertEquals(1, constraintViolations.size());
        assertEquals("must be a well-formed email address", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void existsNoArgsConstructor() {
        User user = new User();

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isNull();
    }

    @Test
    public void existsAllArgsConstructorAndGetters() {

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
    public void verifyEqualsHashcode() {

        User user1 = new User(1L, "zorro@gmail.com", "zorro123", "ADMIN", "zorro");
        User user2 = new User(1L, "zorro@gmail.com", "zorro123", "ADMIN", "zorro");
        User userDifferent = new User(1L, "XXX@gmail.com", "zorro123", "ADMIN", "zorro");

        Assertions.assertThat(user1).isEqualTo(user2);
        Assertions.assertThat(user1).isNotEqualTo(userDifferent);

        Assertions.assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
        Assertions.assertThat(user1.hashCode()).isNotEqualTo(userDifferent.hashCode());

    }
}
