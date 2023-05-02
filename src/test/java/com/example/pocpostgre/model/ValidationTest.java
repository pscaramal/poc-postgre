package com.example.pocpostgre.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationTest {

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

    private static final String JSON_ARRAY = """
            {
              "paises": [
                {
                  "estados": [
                    {
                      "cidades": [
                        {
                          "nome": "Sao Paulo"
                        }
                      ]
                    }
                  ]
                }
              ]
            }""";

    @Test
    void deveRetornarFalseQuandoPathCadastradoNaoExistirNoJson(){
        var validation = Validation.builder()
                .validationId("12345")
                .path("merchant.name")
                .build();

        Boolean validationMatch = validation.checkIfValidationMatch(JSON_ARRAY);

        assertFalse(validationMatch);
    }

    @Test
    void deveRetornarFalseQuandoAsCondicoesCadastradasNaoBateremComOsValoresDoJson(){
        var validation = Validation.builder()
                .validationId("12345")
                .path("paymentMethods[].creditCard.bin")
                .withValue("234597")
                .operator("==")
                .build();

        assertFalse(validation.checkIfValidationMatch(JSON));
    }

    @Test
    void deveRetornarTrueQuandoOValorDoPedidoAtenderAsCondicoesDaValidacao(){
        var validation = Validation.builder()
                .validationId("12345")
                .path("OrderDetails.totalAmount.amountLocalCurrency")
                .withValue("250")
                .operator(">=")
                .validationValueType("DOUBLE")
                .build();

        assertTrue(validation.checkIfValidationMatch(JSON));
    }

    @Test
    void deveRetornarTrueQuandoValorDoBinDoCartaoEstiverContidoEmUmaDasOpcoesDaValidacao(){
        var validation = Validation.builder()
                .validationId("12345")
                .path("paymentMethods[].creditCard.bin")
                .withValue("234597,234596,234598")
                .operator("IN")
                .build();

        assertTrue(validation.checkIfValidationMatch(JSON));
    }
}
