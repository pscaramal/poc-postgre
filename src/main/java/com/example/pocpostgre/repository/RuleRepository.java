package com.example.pocpostgre.repository;

import com.example.pocpostgre.model.Rule;
import reactor.core.publisher.Flux;

public interface RuleRepository {

    Flux<Rule> findAllRules();
    Flux<Rule> findAllRulesBySegment(String segment);
}
