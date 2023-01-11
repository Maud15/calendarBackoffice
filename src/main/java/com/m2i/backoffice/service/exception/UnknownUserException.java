package com.m2i.backoffice.service.exception;

public class UnknownUserException extends Exception {
    public UnknownUserException(){
        super("Unknown user");
    }
}
