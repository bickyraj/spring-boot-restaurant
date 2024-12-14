package com.bickyraj.demo.exception;

public class EmailOrUsernameAlreadyExistException extends RuntimeException {
    public EmailOrUsernameAlreadyExistException() {
        super("Email or username already exist");
    }
}
