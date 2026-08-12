package com.micropool.matchservice.service;

import com.micropool.matchservice.client.ShotServiceClient;
import com.micropool.matchservice.client.ShotServiceUnavailableException;
import com.micropool.matchservice.dto.TakeShotRequest;
import com.micropool.matchservice.model.Match;
import com.micropool.matchservice.repository.MatchRepository;
import com.micropool.matchservice.rules.DefaultMatchRulesEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class MatchServiceFailureTest {

    @Test
    void shotServiceUnavailable_doesNotCorruptMatchState() throws Exception {
        // Arrange
        MatchRepository repository = new MatchRepository();
        ShotServiceClient shotServiceClient = mock(ShotServiceClient.class);
        MatchService matchService = new MatchService(repository, shotServiceClient, new DefaultMatchRulesEngine());

        Match match = new Match("match-1", "alice", "bob");
        repository.save(match);

        doThrow(new ShotServiceUnavailableException("unavailable", null))
                .when(shotServiceClient).takeShot(anyInt(), anyInt(), anyInt());

        TakeShotRequest request = new TakeShotRequest();
        request.setPlayer("alice");
        request.setAngle(30);
        request.setPower(50);

        // Act
        assertThrows(ShotServiceUnavailableException.class, () -> {
            try {
                matchService.takeShot("match-1", request);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Assert - match state is unchanged
        Match saved = repository.findById("match-1").orElseThrow();
        assertEquals("alice", saved.getCurrentTurn());
        assertEquals(7, saved.remainingCount("alice"));
        assertEquals(7, saved.remainingCount("bob"));
    }
}
