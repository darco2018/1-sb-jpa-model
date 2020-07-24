package com.ust.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUsersByUsername(String username);

    List<User> findAllByEmailContaining(String expression);

    List<User> findAllByUsernameLike(String expression);

    List<User> findAllByUsernameBetween(String start, String end);

  @Query(value = "SELECT  COUNT(DISTINCT role) FROM users;", nativeQuery = true)
    int getDisctinctNumberOfUserRolesNative();

  // in JPQL you have to use tha Java name of the entity class; or @Entity(name = "users")
  @Query(value = "SELECT u from users u WHERE u.email = ?1 AND u.username = ?2")
    List<User> getithEmailAndNameJPQL(String email, String username);
}
