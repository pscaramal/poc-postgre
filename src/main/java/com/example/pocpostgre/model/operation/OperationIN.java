package com.example.pocpostgre.model.operation;

import com.example.pocpostgre.model.ValidationValue;

import java.util.Arrays;

public class OperationIN implements Operation{

    @Override
    public boolean makeOperation(String valuePayload, ValidationValue validationValue) {
        return Arrays.stream(validationValue.getValue().split(","))
                .toList()
                .contains(valuePayload);
    }
}
