package com.ust.user;


public class UserNotFoundExcpetion extends RuntimeException {
    public UserNotFoundExcpetion(String errorMessage) {
        super(errorMessage);
    }
    public UserNotFoundExcpetion() {
    }
}
