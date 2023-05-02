package com.example.pocpostgre.model.operation;

import com.example.pocpostgre.model.ValidationValue;

public interface Operation {

    boolean makeOperation(String valuePayload, ValidationValue validationValue);
}
