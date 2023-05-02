package com.example.pocpostgre.controller.dto;

import com.example.pocpostgre.model.Rule;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyRuleDTO {
    private final int returnCode;
    private String ruleId;

    private static final int RETURN_CODE_OK = 0;
    private static final int RETURN_CODE_FRAUD = 99;

    private VerifyRuleDTO(int returnCode){
        this.returnCode = returnCode;
    }

    private VerifyRuleDTO(int returnCode, String ruleId){
        this.returnCode = returnCode;
        this.ruleId = ruleId;
    }
    public static VerifyRuleDTO ORDER_CLEAN(){
        return new VerifyRuleDTO(RETURN_CODE_OK);
    }

    public static VerifyRuleDTO ORDER_FRAUDULENT(Rule rule){
        return new VerifyRuleDTO(RETURN_CODE_FRAUD, rule.getRuleId());
    }
}
