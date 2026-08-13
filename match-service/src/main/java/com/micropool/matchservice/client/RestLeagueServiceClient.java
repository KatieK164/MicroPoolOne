package com.micropool.matchservice.client;

import java.time.Duration;
import java.time.Instant;

import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RestLeagueServiceClient implements LeagueServiceClient {

    private final RestClient restClient = RestClient.builder()
            .baseUrl("http://league-service:8080")
            .requestFactory(ClientHttpRequestFactories.get(
                    ClientHttpRequestFactorySettings.DEFAULTS
                            .withConnectTimeout(Duration.ofSeconds(2))
                            .withReadTimeout(Duration.ofSeconds(3))))
            .build();

    @Override
    //send winner/loser info to league service so it can update the leaderboard
    public void reportResult(String matchId, String winner, String loser) {
        restClient.post()
                .uri("/results")
                .body(new ResultRequest(matchId, winner, loser, Instant.now().toString()))
                .retrieve()
                .toBodilessEntity();
    }

    private record ResultRequest(String matchId, String winner, String loser, String completedAt) {}
}
