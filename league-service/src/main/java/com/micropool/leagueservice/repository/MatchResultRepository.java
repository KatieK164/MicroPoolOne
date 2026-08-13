package com.micropool.leagueservice.repository;

import com.micropool.leagueservice.model.MatchResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchResultRepository extends JpaRepository<MatchResultEntity, Long> {
}
