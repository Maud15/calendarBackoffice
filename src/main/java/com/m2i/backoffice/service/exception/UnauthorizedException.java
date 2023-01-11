package com.m2i.backoffice.service.exception;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String details){
        super("Unauthorized operation : " + details);
    }

}
