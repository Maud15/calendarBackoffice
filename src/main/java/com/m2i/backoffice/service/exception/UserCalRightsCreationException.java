package com.m2i.backoffice.service.exception;

public class UserCalRightsCreationException extends Exception {

    public UserCalRightsCreationException() {
        super("Error while creating User-Calendar relation");
    }

}
