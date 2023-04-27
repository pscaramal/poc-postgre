package com.example.pocpostgre.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Map;

@Table("validation")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Validation {

    @Id
    @Column(value = "validation_id")
    private String id;

    @Column(value = "rule_id")
    private String ruleId;

    @Column(value = "isActive")
    private Boolean active;

    private String path;

    private String operator;

    @Column(value = "value_validation")
    private String value;

    @Column(value = "isValueNumber")
    private Boolean number;

    @Column(value = "isCardValidation")
    private Boolean cardValidation;

    private Validation (String id, String ruleId, Boolean active, String path, String operator, String value, Boolean number, Boolean cardValidation){
        this.id = id;
        this.ruleId = ruleId;
        this.active = active;
        this.path = path;
        this.operator = operator;
        this.value = value;
        this.number = number;
        this.cardValidation = cardValidation;
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
                    .isNumber(Boolean.valueOf(row.get("isValueNumber").toString()))
                    .isCardValidation(Boolean.valueOf(row.get("isCardValidation").toString()))
                    .build();
        }
        return null;
    }
    public static class ValidationBuilder {
        private String id;
        private String ruleId;
        private Boolean active;
        private String path;
        private String operator;
        private String value;
        private Boolean number;
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

        public ValidationBuilder isNumber(Boolean isNumber){
            this.number = isNumber;
            return this;
        }

        public ValidationBuilder isCardValidation(Boolean cardValidation){
            this.cardValidation = cardValidation;
            return this;
        }

        public Validation build(){
            return new Validation(this.id, this.ruleId, this.active, this.path, this.operator, this.value, this.number, this.cardValidation);
        }
    }
}
