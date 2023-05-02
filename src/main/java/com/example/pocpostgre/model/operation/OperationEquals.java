package com.example.pocpostgre.model.operation;

import com.example.pocpostgre.model.ValidationValue;

public class OperationEquals implements Operation{

    @Override
    public boolean makeOperation(String valuePayload, ValidationValue validationValue) {
        return valuePayload.equals(validationValue.getValue());
    }
}
