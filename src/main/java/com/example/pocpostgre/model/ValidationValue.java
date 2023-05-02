package com.example.pocpostgre.model;

public class ValidationValue {
    private final String value;
    private final String valueType;

    public ValidationValue(String value, String type){
        this.value = value;
        this.valueType = type;
    }

    public String getValue() {
        return this.value;
    }

    public String getValueType() {
        return this.valueType;
    }
}
