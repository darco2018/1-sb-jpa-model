package com.ust.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Tag("db")
@DataJpaTest // main, @Component(controllers services, CLRunner)  excluded
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private User USER = null;

    @BeforeEach
    public void setUp() {
        clearRepo();
        USER = new User("zorro@zoho.com", "zorro123", "USER", "zorro");
    }

    // actually only JPA test because no userRepository method is under test
    @Test
    public void testMapping() {
        // arrange
        User found = this.testEntityManager.persistFlushFind(USER);

        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getUsername()).isEqualTo("zorro");
        Assertions.assertThat(found.getEmail()).isEqualTo("zorro@zoho.com");
        Assertions.assertThat(found.getPassword()).isEqualTo("zorro123");
        Assertions.assertThat(found.getRole()).isEqualTo("USER");
    }

    @Test
    public void givenInvalidEmail_whenSaveUser_thenThrowsConstraintViolationException() {
        // arrange
        User blankEmailtUser = new User("", "123", "ADMIN", "zorro1");

            org.junit.jupiter.api.Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            this.testEntityManager.persistAndFlush(blankEmailtUser);
        });

        User nullEmailUser = new User(null, "123", "ADMIN", "zorro2");

        org.junit.jupiter.api.Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
           this.testEntityManager.persistAndFlush(nullEmailUser);
        });

        User invalidEmailUser = new User("abc", "123", "ADMIN", "zorro3");

        org.junit.jupiter.api.Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            this.testEntityManager.persistAndFlush(invalidEmailUser);
        });

        User okEmailUser = new User("abc@server", "12345678", "ADMIN", "zorro4");
        User found = this.testEntityManager.persist(okEmailUser);

        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getUsername()).isEqualTo("zorro4");

    }

    @Test
    public void givenInvalidPassword_whenSaveUser_thenThrowsConstraintViolationException() {
        // arrange
        User blankPassportUser = new User("abc@server1", "", "ADMIN", "zo1");

        org.junit.jupiter.api.Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            this.testEntityManager.persistAndFlush(blankPassportUser);
        });

        User nullPasswordUser = new User("abc@server2", null, "ADMIN", "zo2");

        org.junit.jupiter.api.Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            this.testEntityManager.persistAndFlush(nullPasswordUser);
        });

        User tooShortPasswordUser = new User("abc@server3", "1234567", "ADMIN", "zo3");

        org.junit.jupiter.api.Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            this.testEntityManager.persistAndFlush(tooShortPasswordUser);
        });

        User okPasswordUser = new User("abc@server4", "12345678", "ADMIN", "zo4");
        User found = this.testEntityManager.persist(okPasswordUser);

        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getUsername()).isEqualTo("zo4");
    }

    @Test
    public void givenInvalidUsername_whenSaveUser_thenThrowsConstraintViolationException() {
        // arrange
        User tooShortUsernameUser = new User("abc@server1", "", "ADMIN", "a3");

        org.junit.jupiter.api.Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            this.testEntityManager.persistAndFlush(tooShortUsernameUser);
        });

        User tooLongUsernameUser = new User("abc@server2", null, "ADMIN", "moreThenTwentyChar201");

        org.junit.jupiter.api.Assertions.assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            this.testEntityManager.persistAndFlush(tooLongUsernameUser);
        });


        User okUsernameUser = new User("abc@server4", "12345678", "ADMIN", "zo4");
        User found = this.testEntityManager.persist(okUsernameUser);

        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getUsername()).isEqualTo("zo4");
    }

    // ----------- CRUD tests - actually not needed really --------------------- //
    /*
     They will break if :
     - mapping fails (checked by the 1st test above)
     - constructor changes (checked by the 1st test above)
     - db not connected (checked by the 1st test above)
     - userRepository disappears or doesn't extend (not really - compile time error)
     Conclusion: we don't need them. Let's test only methods where we created JPQL or nativeQueries to
     validate they return what we expect. (these methods at the bottom)
     */


    @Disabled
    @Test
    public void whenSave_savesUser_OK() {

        // act
        assumeTrue(USER.getId() == null);
        this.userRepository.saveAndFlush(USER); // calls save() & then flush() -> item leaves cache

        // assert
        User found = testEntityManager.find(User.class, USER.getId());
        Assertions.assertThat(found.getId()).isNotNull();
        Assertions.assertThat(found.getUsername()).isEqualTo("zorro");
        Assertions.assertThat(found.getEmail()).isEqualTo("zorro@zoho.com");
        Assertions.assertThat(found.getPassword()).isEqualTo("zorro123");
        Assertions.assertThat(found.getRole()).isEqualTo("USER");
    }

   @Disabled
    @Test
    public void givenID_whenDelete_deletesUser_OK() {

        // arrange
        assumeTrue(USER.getId() == null);
        User found = this.testEntityManager.persistFlushFind(USER);

        // act
        this.userRepository.deleteById(found.getId());

        Assertions.assertThat(this.testEntityManager.find(User.class, found.getId())).isNull();
    }

    @Disabled
    @Test
    public void givenUser_whenDelete_deletesUser_OK() {

        // arrange
        assumeTrue(USER.getId() == null);
        User found = this.testEntityManager.persistFlushFind(USER);

        // act
        this.userRepository.delete(found);

        Assertions.assertThat(this.testEntityManager.find(User.class, found.getId())).isNull();
    }

    @Disabled
    @Test
    public void givenSomeFieldsChanged_whenUpdate_updatesUser_OK() {

        //arrange
        assumeTrue(USER.getId() == null);
        User found = this.testEntityManager.persistFlushFind(USER);
        found.setEmail("updated@gmail.com");
        found.setPassword("updated123");

        // act
        this.userRepository.saveAndFlush(found);

        // assert
        User updated = testEntityManager.find(User.class, found.getId());

        Assertions.assertThat(updated.getId()).isEqualTo(found.getId());
        Assertions.assertThat(updated.getEmail()).isEqualTo("updated@gmail.com");
        Assertions.assertThat(updated.getPassword()).isEqualTo("updated123");

        Assertions.assertThat(updated.getUsername()).isEqualTo(found.getUsername());
        Assertions.assertThat(updated.getRole()).isEqualTo(found.getRole());
    }

    @Disabled
    @Test
    public void givenUsername_findsUser_OK() {
        //arrange
        assumeTrue(USER.getId() == null);
        this.testEntityManager.persistAndFlush(USER);

        // act
        Optional<User> optUser = this.userRepository.findByUsername(USER.getUsername());

        //assert
        Assertions.assertThat(optUser.get().getUsername()).isEqualTo(USER.getUsername());
    }

    // ----------- Testing "own" methods: native SQL & JPQL --------------------- //
    /*
        They will break if sb removes method by accident.
        or the conditions stated at the top of page under the 1st test
     */

    @Test
    public void countsDistinctUserRolesNative() {

        // arrange
        User userWithUserRole_1 = new User("sadsa3@zoho.com", "wersefsdfdsaf", "USER", "saWEafwe");
        User userWithUserRole_2 = new User("xydasdFDASFD@zoho.com", "werwrewrewfsd", "USER", "xysdsASadkl");
        User userWithAdminRole = new User("cdsodlk@zoho.com", "qwjkkdfklllklkl", "ADMIN", "ASDASDew");
        testEntityManager.persistAndFlush(userWithUserRole_1);
        testEntityManager.persistAndFlush(userWithUserRole_2);
        testEntityManager.persistAndFlush(userWithAdminRole);

        // act
        int noOfdistinctRoles = this.userRepository.getNumberOfDistinctUserRolesNative();

        // assert
        Assertions.assertThat(noOfdistinctRoles).isEqualTo(2);
    }

    @Test
    public void givenEmailAndUsername_returnsSingleUser() {

        // arrange

        User user1 = new User("salsa@zoho.com", "wersefsdfdsaf", "USER", "salsa");
        User user2 = new User("pizza@zoho.com", "xxdasdsadsawfsd", "USER", "pizza");
        User user3 = new User("hgjls@zoho.com", "qwjkkdfklllklkl", "ADMIN", "ASDASDew");
        testEntityManager.persistAndFlush(user1);
        testEntityManager.persistAndFlush(user2);
        testEntityManager.persistAndFlush(user3);

        // act
        List<User> found = this.userRepository.getUserByEmailAndUsernameJPQL("salsa@zoho.com", "salsa");

        // assert
        Assertions.assertThat(found.size()).isEqualTo(1);
        Assertions.assertThat(found.get(0).getUsername()).isEqualTo("salsa");
    }

    private void clearRepo() {
        this.userRepository.deleteAllInBatch();
        assumeTrue(this.userRepository.count() == 0);
    }

}
