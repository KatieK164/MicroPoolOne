package com.micropool.leagueservice.service;

import com.micropool.leagueservice.dto.LeaderboardEntry;
import com.micropool.leagueservice.dto.RecordResultRequest;
import com.micropool.leagueservice.model.MatchResultEntity;
import com.micropool.leagueservice.repository.MatchResultRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LeagueService {

    private final MatchResultRepository repository;

    public LeagueService(MatchResultRepository repository) {
        this.repository = repository;
    }

    public void recordResult(RecordResultRequest request) {
        MatchResultEntity entity = new MatchResultEntity(
                request.getMatchId(),
                request.getWinner(),
                request.getLoser(),
                Instant.now()
        );
        repository.save(entity);
    }

    public List<LeaderboardEntry> getLeaderboard() {
        List<MatchResultEntity> results = repository.findAll();

        Map<String, Long> winsByPlayer = results.stream()
                .collect(Collectors.groupingBy(MatchResultEntity::getWinner, Collectors.counting()));

        Map<String, Long> lossesByPlayer = results.stream()
                .collect(Collectors.groupingBy(MatchResultEntity::getLoser, Collectors.counting()));

        return winsByPlayer.entrySet().stream()
                .map(e -> new LeaderboardEntry(
                        e.getKey(),
                        e.getValue().intValue(),
                        lossesByPlayer.getOrDefault(e.getKey(), 0L).intValue()
                ))
                .sorted(Comparator.comparingInt(LeaderboardEntry::getWins).reversed())
                .collect(Collectors.toList());
    }
}
