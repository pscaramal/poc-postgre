package com.example.pocpostgre.repository.impl;

import com.example.pocpostgre.model.Rule;
import com.example.pocpostgre.repository.RuleRepository;
import org.flywaydb.core.internal.database.base.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class RuleRepositoryImpl implements RuleRepository {

    private static final String QUERY_FIND_ALL_RULES =
            "SELECT r.rule_id, r.rule_name, r.segment, r.isActive rule_active, v.validation_id,"  +
            "       v.isActive v_active, v.path, v.operator," +
            "       v.value_validation, v.isValueNumber, v.isCardValidation " +
            "FROM rule r JOIN validation v ON r.rule_id = v.rule_id " +
            "ORDER BY r.rule_id;";
    private static final String QUERY_FIND_ACTIVE_RULES_BY_SEGMENT =
            "SELECT r.rule_id, r.rule_name, r.segment, r.isActive rule_active, v.validation_id," +
            "       v.isActive v_active, v.path, v.operator," +
            "       v.value_validation, v.isValueNumber, v.isCardValidation " +
            "FROM rule r JOIN validation v ON r.rule_id = v.rule_id " +
            "WHERE r.isactive = true " +
            "AND r.segment = :segment " +
            "ORDER BY r.rule_id;";

    @Autowired
    private DatabaseClient client;

    @Override
    public Flux<Rule> findAllRules() {
        return client.sql(QUERY_FIND_ALL_RULES)
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("rule_id"))
                .flatMap(Rule::fromRow);
    }

    @Override
    public Flux<Rule> findAllRulesBySegment(String segment) {

        return client.sql(QUERY_FIND_ACTIVE_RULES_BY_SEGMENT)
                .bind("segment", segment)
                .fetch()
                .all()
                .bufferUntilChanged(result -> result.get("rule_id"))
                .flatMap(Rule::fromRow);
    }
}
