package com.example.pocpostgre.service;

import com.example.pocpostgre.model.Rule;
import com.example.pocpostgre.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class RuleService {

    @Autowired
    private RuleRepository repository;

    public Flux<Rule> getAllRules(){
        return repository.findAllRules();
    }

    public Flux<Rule> getAllRulesBySegment(String segment){
        return repository.findAllRulesBySegment(segment);
    }
}
