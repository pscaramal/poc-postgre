package com.example.pocpostgre.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Rule {

    private String id;

    private String name;

    private String segment;

    private Boolean active;

    private List<Validation> validations;

    private Rule (String id, String name, String segment, Boolean active, List<Validation> validations){
        this.id = id;
        this.name = name;
        this.segment = segment;
        this.active = active;
        this.validations = validations;
    }

    public static Mono<Rule> fromRow(List<Map<String, Object>> rows){
        return Mono.just(Rule.builder()
                        .ruleId(rows.get(0).get("rule_id").toString())
                        .ruleName(rows.get(0).get("rule_name").toString())
                        .ofSegment(rows.get(0).get("segment").toString())
                        .isActive(Boolean.valueOf(rows.get(0).get("rule_active").toString()))
                        .withValidations(rows.stream()
                                .map(Validation::fromRow)
                                .filter(Objects::nonNull)
                                .toList())
                .build());
    }

    public static RuleBuilder builder(){
        return new RuleBuilder();
    }
    public static class RuleBuilder {
        private String id;
        private String name;
        private String segment;
        private Boolean active;
        private List<Validation> validations;

        public RuleBuilder ruleId(String id){
            this.id = id;
            return this;
        }

        public RuleBuilder ruleName(String name){
            this.name = name;
            return this;
        }

        public RuleBuilder ofSegment(String segment){
            this.segment = segment;
            return this;
        }

        public RuleBuilder isActive(Boolean active){
            this.active = active;
            return this;
        }

        public RuleBuilder withValidations(List<Validation> validations) {
            this.validations = validations;
            return this;
        }

        public Rule build(){
            return new Rule(this.id, this.name, this.segment, this.active, this.validations);
        }

    }
}
