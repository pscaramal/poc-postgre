package com.example.pocpostgre.model;

import com.example.pocpostgre.controller.dto.ValidationResponseDTO;

import java.util.Map;

public class Validation {

    private final String id;

    private final String ruleId;

    private final Boolean active;

    private final Path path;

    private final Operator operator;

    private final ValidationValue validationValue;

    private final Boolean cardValidation;

    private Validation (String id, String ruleId, Boolean active, Path path, Operator operator,
                        Boolean cardValidation, ValidationValue validationValue){
        this.id = id;
        this.ruleId = ruleId;
        this.active = active;
        this.path = path;
        this.operator = operator;
        this.cardValidation = cardValidation;
        this.validationValue = validationValue;
    }

    public static ValidationBuilder builder(){
        return new ValidationBuilder();
    }
    public static Validation fromRow(Map<String, Object> row){
        if (row.get("validation_id") != null){
            return Validation.builder()
                    .validationId(row.get("validation_id").toString())
                    .ruleId(row.get("rule_id").toString())
                    .isActive(Boolean.valueOf(row.get("v_active").toString()))
                    .path(row.get("path").toString())
                    .operator(row.get("operator").toString())
                    .withValue(row.get("value_validation").toString())
                    .validationValueType(row.get("value_validation_type") != null? row.get("value_validation_type").toString() : "")
                    .isCardValidation(Boolean.valueOf(row.get("isCardValidation").toString()))
                    .build();
        }
        return null;
    }

    public ValidationResponseDTO toDto() {
        return ValidationResponseDTO.builder()
                .validationId(this.id)
                .ruleId(this.ruleId)
                .isActive(this.active)
                .path(this.path.getPath())
                .operator(this.operator.getOperation())
                .withValue(this.validationValue.getValue())
                .valueType(this.validationValue.getValueType())
                .isCardValidation(this.cardValidation)
                .build();
    }

    public Boolean checkIfValidationMatch(String json) {
         String valueOfJson = this.path.getValueFromJson(json);

         if (valueOfJson == null){
             return false;
         }

         return this.operator.verifyOperation(valueOfJson, this.validationValue);
    }

    public static class ValidationBuilder {
        private String id;
        private String ruleId;
        private Boolean active;
        private String path;
        private String operator;
        private String value;
        private String validationValueType;
        private Boolean cardValidation;

        public ValidationBuilder validationId(String id){
            this.id = id;
            return this;
        }

        public ValidationBuilder ruleId(String ruleId){
            this.ruleId = ruleId;
            return this;
        }

        public ValidationBuilder isActive(Boolean active){
            this.active = active;
            return this;
        }

        public ValidationBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ValidationBuilder operator(String operator) {
            this.operator = operator;
            return this;
        }

        public ValidationBuilder withValue(String value){
            this.value = value;
            return this;
        }

        public ValidationBuilder validationValueType(String validationValueType){
            this.validationValueType = validationValueType;
            return this;
        }

        public ValidationBuilder isCardValidation(Boolean cardValidation){
            this.cardValidation = cardValidation;
            return this;
        }

        public Validation build(){
            return new Validation(
                    this.id,
                    this.ruleId,
                    this.active,
                    new Path(this.path),
                    new Operator(this.operator),
                    this.cardValidation,
                    new ValidationValue(this.value, this.validationValueType));
        }
    }
}
