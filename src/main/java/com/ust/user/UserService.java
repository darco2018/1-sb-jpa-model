package com.ust.user;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    // Unrecommended alternative:
    // @Autowired
    // private  UserRepository userRepository;
    @NonNull private final  UserRepository userRepository;

   /* public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

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
        return  userRepository.getEmailAndNameJPQL(email, username);
    }

    public Optional<User> getUserByUsername(String username) {
        return  userRepository.findByUsername(username);
    }
}

