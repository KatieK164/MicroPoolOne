package com.micropool.matchservice.service;

import com.micropool.matchservice.dto.CreateMatchRequest;
import com.micropool.matchservice.dto.MatchResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MatchControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void createMatch_returns201WithMatchId() {
        CreateMatchRequest request = new CreateMatchRequest();
        request.setPlayer1("alice");
        request.setPlayer2("bob");

        ResponseEntity<MatchResponse> response = restTemplate.postForEntity(
                "/matches", request, MatchResponse.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().matchId);
        assertEquals("alice", response.getBody().currentTurn);
    }
}

