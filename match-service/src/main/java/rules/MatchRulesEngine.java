package com.micropool.matchservice.rules;
import com.micropool.matchservice.model.Match;
import com.micropool.matchservice.model.ShotOutcome;
public interface MatchRulesEngine {
      ShotProcessingResult process(Match match, String player, ShotOutcome outcome);
  }
