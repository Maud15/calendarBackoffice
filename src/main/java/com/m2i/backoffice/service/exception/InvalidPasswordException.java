package com.m2i.backoffice.service.exception;

public class InvalidPasswordException extends Exception {

    public InvalidPasswordException() {
        super("Password does not meet the requirements");
    }

}
