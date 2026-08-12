package com.micropool.matchservice.rules;
public record ShotProcessingResult(
      boolean foul,
      int ballsPotted,
      boolean turnChanged,
      boolean matchEnded,
      String winner
  ) {}