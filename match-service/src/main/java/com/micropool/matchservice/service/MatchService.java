package com.micropool.matchservice.service;

import com.micropool.matchservice.client.ShotServiceClient;
import com.micropool.matchservice.dto.CreateMatchRequest;
import com.micropool.matchservice.dto.MatchResponse;
import com.micropool.matchservice.dto.TakeShotRequest;
import com.micropool.matchservice.dto.TakeShotResponse;
import com.micropool.matchservice.model.Match;
import com.micropool.matchservice.model.MatchStatus;
import com.micropool.matchservice.model.ShotOutcome;
import com.micropool.matchservice.repository.MatchRepository;
import com.micropool.matchservice.rules.MatchRulesEngine;
import com.micropool.matchservice.rules.ShotProcessingResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class MatchService {

    private final MatchRepository repository;
    private final ShotServiceClient shotServiceClient;
    private final MatchRulesEngine rulesEngine;

    public MatchService(MatchRepository repository, ShotServiceClient shotServiceClient,
                         MatchRulesEngine rulesEngine) {
        this.repository = repository;
        this.shotServiceClient = shotServiceClient;
        this.rulesEngine = rulesEngine;
    }

    public MatchResponse createMatch(CreateMatchRequest request) {
        Match match = new Match(UUID.randomUUID().toString(), request.getPlayer1(), request.getPlayer2());
        repository.save(match);
        return MatchResponse.from(match);
    }

    public MatchResponse getMatch(String matchId) {
        return MatchResponse.from(findOrThrow(matchId));
    }

    public TakeShotResponse takeShot(String matchId, TakeShotRequest request) {
        Match match = findOrThrow(matchId);

        if (match.getStatus() != MatchStatus.ACTIVE || !request.getPlayer().equals(match.getCurrentTurn())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "It is " + match.getCurrentTurn() + "'s turn.");
        }

        ShotOutcome outcome = shotServiceClient.takeShot(request.getAngle(), request.getPower(), request.getSpin());

        ShotProcessingResult result = rulesEngine.process(match, request.getPlayer(), outcome);
        repository.save(match);

        return new TakeShotResponse(outcome.result().name(), result.ballsPotted(),
                result.foul(), result.turnChanged(), MatchResponse.from(match));
    }

    private Match findOrThrow(String matchId) {
        return repository.findById(matchId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
    }
}
