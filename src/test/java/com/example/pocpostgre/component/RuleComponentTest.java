package com.example.pocpostgre.component;

import com.example.pocpostgre.controller.dto.RuleResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class RuleComponentTest {

    @Autowired
    private WebTestClient webTestClient;

    private final static String RULE_LASA_URL = "/rules?segment=LASA";

    @Test
    void getRulesBySegment(){

        webTestClient
                .get()
                .uri(RULE_LASA_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(RuleResponseDTO.class)
                .hasSize(1)
                .value(rules -> {
                    rules.forEach(ruleResponseDTO -> {
                        assertNotNull(ruleResponseDTO.getSegment());
                        assertEquals("LASA", ruleResponseDTO.getSegment());
                    });
                });
    }
}
