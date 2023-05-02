package com.example.pocpostgre.controller;

import com.example.pocpostgre.controller.dto.VerifyRuleDTO;
import com.example.pocpostgre.controller.dto.VerifyRuleRequest;
import com.example.pocpostgre.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/verify")
public class VerifyOrderController {

    @Autowired
    private RuleService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<VerifyRuleDTO> verifyRule(@RequestBody VerifyRuleRequest order){

        return service.verifyRulesInOrder(order);
    }
}
