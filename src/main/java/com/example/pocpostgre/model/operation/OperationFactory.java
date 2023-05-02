package com.example.pocpostgre.model.operation;

import java.util.HashMap;
import java.util.Map;

public class OperationFactory {

    private static Map<String, Operation> operations = new HashMap<>();

    static {
        operations.put("IN", new OperationIN());
        operations.put("==", new OperationEquals());
        operations.put(">=", new OperationGreaterEquals());
    }

    public static Operation getOperation(String operation){
        return operations.get(operation);
    }
}
