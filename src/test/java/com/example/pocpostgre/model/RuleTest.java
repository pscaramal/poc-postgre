package com.example.pocpostgre.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RuleTest {

    private static final String JSON = """
            {
              "order": {
                "id": "0b972d05-cc5e-4f5f-a736-f2e8a6a21ebf",
                "type": "PURCHASE"
              },
              "merchant": {
                "id": "b5fb228a-dc57-416f-8183-43e35bf5c9a6"
              },
              "paymentMethods": [
                {
                  "type": "DIGITAL_WALLET",
                  "amount": {
                    "amountLocalCurrency": 0.75,
                    "currency": "BRL"
                  }
                },
                {
                  "type": "CREDIT_CARD",
                  "creditCard": {
                    "nameOnCard": "Joao da Silva",
                    "bin": "234596",
                    "cardType": "CREDIT"
                  },
                  "amount": {
                    "amountLocalCurrency": 87.93,
                    "currency": "BRL"
                  }
                }
              ],
               "OrderDetails": {
                 "totalAmount": {
                   "amountLocalCurrency": 350.5
                 }
               }
            }""";

    @Test
    void deveRetornarTrueQuandoTodasAsCondicaoDasValidacoesForemVerdadeiras(){

        var rule = Rule.builder()
                .ruleId("12324")
                .withValidations(List.of(
                        Validation.builder()
                                .operator("IN")
                                .path("paymentMethods[].creditCard.bin")
                                .withValue("55555,234596,666666")
                                .build(),
                        Validation.builder()
                                .operator("==")
                                .path("merchant.id")
                                .withValue("b5fb228a-dc57-416f-8183-43e35bf5c9a6")
                                .build(),
                        Validation.builder()
                                .operator(">=")
                                .path("OrderDetails.totalAmount.amountLocalCurrency")
                                .withValue("350.5")
                                .validationValueType("DOUBLE")
                                .build()
                ))
                .build();

        assertTrue(rule.verifyValidations(JSON));
    }

    @Test
    void deveRetornarFalseQuandoAlgumaCondicaoDaValidacaoNaoDerMatch(){
        var rule = Rule.builder()
                .ruleId("12345")
                .withValidations(List.of(
                        Validation.builder()
                                .path("order.type")
                                .operator("==")
                                .withValue("PURCHASE")
                                .build(),
                        Validation.builder()
                                .path("merchant.id")
                                .withValue("123456789")
                                .operator("==")
                                .build()
                ))
                .build();

        assertFalse(rule.verifyValidations(JSON));
    }
}
