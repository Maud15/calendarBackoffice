package com.m2i.backoffice.service.exception;

public class UnknownValueException extends Exception {
    public UnknownValueException(String element, String value){
        super("Unknown value -" + value + "- for element -" + element + "-");
    }
}
