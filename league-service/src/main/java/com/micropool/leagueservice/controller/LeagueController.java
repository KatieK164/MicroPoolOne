package com.micropool.leagueservice.controller;

import com.micropool.leagueservice.dto.LeaderboardEntry;
import com.micropool.leagueservice.dto.RecordResultRequest;
import com.micropool.leagueservice.service.LeagueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeagueController {

    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @PostMapping("/results")
    public ResponseEntity<Void> recordResult(@Valid @RequestBody RecordResultRequest request) {
        leagueService.recordResult(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/leaderboard")
    public List<LeaderboardEntry> getLeaderboard() {
        return leagueService.getLeaderboard();
    }
}
