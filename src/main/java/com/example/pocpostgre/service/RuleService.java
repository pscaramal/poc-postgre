package com.example.pocpostgre.service;

import com.example.pocpostgre.controller.dto.VerifyRuleDTO;
import com.example.pocpostgre.controller.dto.VerifyRuleRequest;
import com.example.pocpostgre.model.Rule;
import com.example.pocpostgre.repository.RuleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RuleService {

    @Autowired
    private RuleRepository repository;

    private final ObjectWriter writer = new ObjectMapper().writer();

    public Flux<Rule> getAllRules(){
        return repository.findAllRules();
    }

    public Flux<Rule> getAllRulesBySegment(String segment){
        return repository.findAllRulesBySegment(segment);
    }

    public Mono<VerifyRuleDTO> verifyRulesInOrder(VerifyRuleRequest request){
        var json = getJsonFromRequest(request);

        return repository.findAllRulesBySegment(request.getSegment())
                .filter(rule -> rule.verifyValidations(json))
                .map(VerifyRuleDTO::ORDER_FRAUDULENT)
                .take(1)
                .switchIfEmpty(Mono.just(VerifyRuleDTO.ORDER_CLEAN()))
                .single();
    }

    private String getJsonFromRequest(VerifyRuleRequest request) {
        String json = null;
        try {
            json = writer.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            json = "{}";
        }
        return json;
    }
}
