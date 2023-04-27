package com.example.pocpostgre.controller;

import com.example.pocpostgre.model.Rule;
import com.example.pocpostgre.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleService service;

    private static final Logger log = LoggerFactory.getLogger(RuleController.class);
    @GetMapping
    public Flux<Rule> getRules(@RequestParam(name = "segment", required = false) String segment){
        log.info("Inciando {}", segment);

        if (segment == null || segment.isBlank()){
            log.info("gettingAllRules");
            return service.getAllRules();
        }

        log.info("gettingRulesBySegment");
        return service.getAllRulesBySegment(segment);
    }

}
