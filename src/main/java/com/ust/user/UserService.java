package com.ust.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByUsername(String username) {
        return userRepository.findAllByUsername(username);
    }

    public List<User> getUsersByEmailContaining(String expression) {
        return userRepository.findAllByEmailContaining(expression);
    }

    public List<User> getsAllUsersWhereNameLike(String expression) {
        return userRepository.findAllByUsernameLike(expression);
    }

    public List<User> getsAllWhereNameBetween(String start, String end) {
        return userRepository.findAllByUsernameBetween(start, end);
    }

    public int getDisctinctNoOfUserRoles() {
        return  userRepository.getDisctinctNumberOfUserRolesNative();
    }

    public List<User> getUsersWithEmailAndName(String email, String username) {
        return  userRepository.getithEmailAndNameJPQL(email, username);
    }
}
