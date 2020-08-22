package com.ust.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByUsername(String username);

    List<User> findAllByEmailContaining(String expression);

    List<User> findAllByUsernameLike(String expression);

    List<User> findAllByUsernameBetween(String start, String end);

  @Query(value = "SELECT  COUNT(DISTINCT role) FROM users;", nativeQuery = true)
    int getNumberOfDistinctUserRolesNative();

  // in JPQL you have to use tha Java name of the entity class; or @Entity(name = "users")
    // When using parameters don't use semicolon at the end of query!!!
  @Query(value = "SELECT u from User u WHERE u.email = ?1 AND u.username = ?2")
    List<User> getUserByEmailAndUsernameJPQL(String email, String username);

    Optional<User> findByUsername(String darek);
}
