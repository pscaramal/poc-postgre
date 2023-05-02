package com.example.pocpostgre.model.operation;

import com.example.pocpostgre.model.ValidationValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperationGreaterEquals implements Operation{
    private static final Logger log = LoggerFactory.getLogger(OperationGreaterEquals.class);

    @Override
    public boolean makeOperation(String valuePayload, ValidationValue validationValue) {
        if (validationValue.getValueType().equals("DOUBLE")){
            try {
                var valueOfJsonDouble = Double.valueOf(valuePayload);
                var valueOfValidation = Double.valueOf(validationValue.getValue());
                return valueOfJsonDouble.compareTo(valueOfValidation) >= 0;
            } catch(NumberFormatException e){
                log.error("Erro ao fazer parse");
                return false;
            }
        }
        return false;
    }
}
