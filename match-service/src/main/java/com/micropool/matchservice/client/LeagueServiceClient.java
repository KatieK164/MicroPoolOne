package com.micropool.matchservice.client;

public interface LeagueServiceClient {
    void reportResult(String matchId, String winner, String loser);
}
