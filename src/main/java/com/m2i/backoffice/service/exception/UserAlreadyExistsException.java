package com.m2i.backoffice.service.exception;

import java.util.Objects;

public class UserAlreadyExistsException extends Exception {

    private final String dataName;
    private final String data;

    public UserAlreadyExistsException(String dataName, String data) {
        super(dataName + " : " + data + " already exists");
        this.dataName = getFrench(dataName);
        this.data = getFrench(data);
    }

    private String getFrench(String data) {
        if(Objects.equals(data, "pseudo")) {
            return "Identifiant";
        } else if(Objects.equals(data, "email")) {
            return "Email";
        }
        return data;
    }

    public String getDataName() {
        return dataName;
    }
    public String getData() {
        return data;
    }
}
