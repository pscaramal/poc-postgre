package com.example.pocpostgre.controller.dto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ValidationResponseDTO {

    private final String id;

    private final String ruleId;

    private final Boolean active;

    private final String path;

    private final String operator;

    private final String value;

    private final String valueType;
    private final Boolean cardValidation;

    private ValidationResponseDTO(String id, String ruleId, Boolean active, String path, String operator, String value,
                                  Boolean cardValidation, String valueType) {
        this.id = id;
        this.ruleId = ruleId;
        this.active = active;
        this.path = path;
        this.operator = operator;
        this.value = value;
        this.cardValidation = cardValidation;
        this.valueType = valueType;
    }

    public static ValidationResponseDTOBuilder builder(){
        return new ValidationResponseDTOBuilder();
    }

    public static class ValidationResponseDTOBuilder {
        private String id;
        private String ruleId;
        private Boolean active;
        private String path;
        private String operator;
        private String value;
        private Boolean cardValidation;

        private String valueType;

        public ValidationResponseDTOBuilder validationId(String id){
            this.id = id;
            return this;
        }

        public ValidationResponseDTOBuilder ruleId(String ruleId){
            this.ruleId = ruleId;
            return this;
        }

        public ValidationResponseDTOBuilder isActive(Boolean active){
            this.active = active;
            return this;
        }

        public ValidationResponseDTOBuilder path(String path) {
            this.path = path;
            return this;
        }

        public ValidationResponseDTOBuilder operator(String operator) {
            this.operator = operator;
            return this;
        }

        public ValidationResponseDTOBuilder withValue(String value){
            this.value = value;
            return this;
        }

        public ValidationResponseDTOBuilder isCardValidation(Boolean cardValidation){
            this.cardValidation = cardValidation;
            return this;
        }

        public ValidationResponseDTOBuilder valueType(String valueType){
            this.valueType = valueType;
            return this;
        }

        public ValidationResponseDTO build(){
            return new ValidationResponseDTO(this.id, this.ruleId, this.active, this.path, this.operator, this.value,
                    this.cardValidation, this.valueType);
        }
    }
}
