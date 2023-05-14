package com.example.pocpostgre.controller.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RuleResponseDTO {
    private final String id;

    private final String name;

    private final String segment;

    private  Boolean active;

    private final List<ValidationResponseDTO> validations;

    public String getSegment() {
        return segment;
    }

    public RuleResponseDTO(String id, String name, String segment, Boolean active, List<ValidationResponseDTO> validations) {
        this.id = id;
        this.name = name;
        this.segment = segment;
        this.active = active;
        this.validations = validations;
    }

    public static RuleResponseDTOBuilder builder() {
        return new RuleResponseDTOBuilder();
    }

    public static class RuleResponseDTOBuilder {
        private String id;
        private String name;
        private String segment;
        private Boolean active;
        private List<ValidationResponseDTO> validations;

        public RuleResponseDTOBuilder ruleId(String id) {
            this.id = id;
            return this;
        }

        public RuleResponseDTOBuilder ruleName(String name) {
            this.name = name;
            return this;
        }

        public RuleResponseDTOBuilder ofSegment(String segment) {
            this.segment = segment;
            return this;
        }

        public RuleResponseDTOBuilder isActive(Boolean active) {
            this.active = active;
            return this;
        }

        public RuleResponseDTOBuilder withValidations(List<ValidationResponseDTO> validations) {
            this.validations = validations;
            return this;
        }

        public RuleResponseDTO build() {
            return new RuleResponseDTO(this.id, this.name, this.segment, this.active, this.validations);
        }
    }
}
