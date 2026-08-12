package com.micropool.matchservice.repository;

import com.micropool.matchservice.model.Match;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MatchRepository {
    private final ConcurrentHashMap<String, Match> matches = new ConcurrentHashMap<>();

    public Match save(Match match) {
        matches.put(match.getMatchId(), match);
        return match;
    }

    public Optional<Match> findById(String matchId) {
        return Optional.ofNullable(matches.get(matchId));
    }
}
