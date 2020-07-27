package com.ust.user;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    // Unrecommended alternative to @RequiredArgsConstructor construcotr injection:
    // @Autowired
    // private  UserRepository userRepository;
    @NonNull
    private final UserRepository userRepository;

    // C_UD

    public User createUser(User user) {
        Objects.requireNonNull(user, "Cannot save a null User");
        return this.userRepository.save(user);
    }

    public User updateUser(User user) {
        Objects.requireNonNull(user, "Cannot update a null User");
        return this.userRepository.save(user);
    }

    public void deleteUser(User user) {
        Objects.requireNonNull(user, "Cannot delete a null User");
        this.userRepository.delete(user);
    }

    public void deleteUserbyId(long id) {
        this.userRepository.deleteById(id);
    }

    // ------------ basic find ---------------------------

    public boolean isUserExistsById(long id) {
        return this.userRepository.existsById(id);
    }

    public User findUserbyId(long id) {
        Optional<User> userOpt = this.userRepository.findById(id);

        return userOpt.orElseThrow(() -> new UserNotFoundExcpetion("No user with id" + id + " in the database."));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByEmail(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findUserByEmailAndUsername(String email, String username) {
        return userRepository.getEmailAndNameJPQL(email, username);
    }

    // ------------ more complex find ---------------------------

    public List<User> findAllUsersByEmailContaining(String expression) {
        return userRepository.findAllByEmailContaining(expression);
    }

    public List<User> findAllUsersWhereUsernameLike(String expression) {
        return userRepository.findAllByUsernameLike(expression);
    }

    public List<User> findAllUsersWhereUsernameBetween(String start, String end) {
        return userRepository.findAllByUsernameBetween(start, end);
    }

    public int getDisctinctNoOfUserRoles() {
        return userRepository.getDisctinctNumberOfUserRolesNative();
    }

    // ------------ helper ---------------------------

}

