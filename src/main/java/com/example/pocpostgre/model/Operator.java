package com.example.pocpostgre.model;

import com.example.pocpostgre.model.operation.Operation;
import com.example.pocpostgre.model.operation.OperationFactory;

public class Operator {
    private final Operation operation;
    private final String operationSign;

    public Operator (String operation){
        this.operation = OperationFactory.getOperation(operation);
        this.operationSign = operation;
    }

    public boolean verifyOperation(String valuePayload, ValidationValue validationValue){
        return this.operation.makeOperation(valuePayload, validationValue);
    }
    public String getOperation() {
        return this.operationSign;
    }
}
