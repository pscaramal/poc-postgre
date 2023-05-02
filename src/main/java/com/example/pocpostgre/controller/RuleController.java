package com.example.pocpostgre.controller;

import com.example.pocpostgre.controller.dto.RuleResponseDTO;
import com.example.pocpostgre.controller.dto.VerifyRuleRequest;
import com.example.pocpostgre.model.Rule;
import com.example.pocpostgre.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleService service;

    private static final Logger log = LoggerFactory.getLogger(RuleController.class);
    @GetMapping
    public Flux<RuleResponseDTO> getRules(@RequestParam(name = "segment", required = false) String segment){
        log.info("Inciando {}", segment);

        if (segment == null || segment.isBlank()){
            log.info("gettingAllRules");
            return service.getAllRules()
                    .map(Rule::toDTO);
        }

        log.info("gettingRulesBySegment");
        return service.getAllRulesBySegment(segment)
                .map(Rule::toDTO);
    }
}
