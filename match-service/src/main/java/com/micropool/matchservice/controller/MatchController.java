package com.micropool.matchservice.controller;

import com.micropool.matchservice.dto.CreateMatchRequest;
import com.micropool.matchservice.dto.MatchResponse;
import com.micropool.matchservice.dto.TakeShotRequest;
import com.micropool.matchservice.dto.TakeShotResponse;
import com.micropool.matchservice.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    public ResponseEntity<MatchResponse> create(@Valid @RequestBody CreateMatchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(request));
    }

    @GetMapping("/{id}")
    public MatchResponse get(@PathVariable String id) {
        return matchService.getMatch(id);
    }

    @PostMapping("/{id}/shots")
    public TakeShotResponse takeShot(@PathVariable String id, @Valid @RequestBody TakeShotRequest request) throws InterruptedException {
        return matchService.takeShot(id, request);
    }
}
