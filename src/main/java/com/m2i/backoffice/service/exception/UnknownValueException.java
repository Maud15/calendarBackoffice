package com.m2i.backoffice.service.exception;

public class UnknownValueException extends Exception {

    private final String dataName;
    private final String data;

    public UnknownValueException(String dataName, String data){
        super("Unknown value -" + data + "- for element -" + dataName + "-");
        this.dataName = dataName;
        this.data = data;
    }

    public String getDataName() {
        return dataName;
    }
    public String getData() {
        return data;
    }
}
