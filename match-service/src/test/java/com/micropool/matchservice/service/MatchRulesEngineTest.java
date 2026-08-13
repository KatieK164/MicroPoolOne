package com.micropool.matchservice.service;

import com.micropool.matchservice.model.Match;
import com.micropool.matchservice.model.MatchStatus;
import com.micropool.matchservice.model.ShotOutcome;
import com.micropool.matchservice.model.ShotResult;
import com.micropool.matchservice.rules.DefaultMatchRulesEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import static org.testng.AssertJUnit.*;

public class MatchRulesEngineTest {

    private DefaultMatchRulesEngine rulesEngine;
    private Match match;
    private static final String P1 = "Tadhg";
    private static final String P2 = "Alan";

    @BeforeEach
    void setUp() {
        rulesEngine = new DefaultMatchRulesEngine();
        match = new Match("m1",P1, P2);
    }

    @Test
    void foul_switchesTurn(){
        var outcome = new ShotOutcome(ShotResult.FOUL, 0);
        int before = match.remainingCount(P1);
        var result = rulesEngine.process(match, P1,outcome);
        assertTrue(result.foul());
        assertTrue(result.turnChanged());
        assertEquals(P2, match.getCurrentTurn());
        assertEquals(before, match.remainingCount(P1));
    }

    @Test
    void miss_switchesTurn(){
        var outcome = new ShotOutcome(ShotResult.MISS, 2);
        var result = rulesEngine.process(match, P1,outcome);
        assertFalse(result.foul());
        assertTrue(result.turnChanged());
        assertEquals(P2, match.getCurrentTurn());
    }

    @Test
    void legitimatewin(){
        match.removeBalls(P1, 7);
        var outcome = new ShotOutcome(ShotResult.POT_ONE, 9);
        var result = rulesEngine.process(match, P1,outcome);
        assertTrue(result.matchEnded());
        assertEquals(P1, result.winner());
        assertEquals(MatchStatus.COMPLETED,match.getStatus());
    }

    @Test
    void PotONERemovesOneBall(){
        int before = match.remainingCount(P1);
        var outcome = new ShotOutcome(ShotResult.POT_ONE, 9);
        var result = rulesEngine.process(match, P1,outcome);

        assertFalse(result.turnChanged());
        assertEquals(P1, match.getCurrentTurn());
        assertEquals(before -1 , match.remainingCount(P1));
    }

    @Test
    void Illegal8Ball(){
        match.removeBalls(P1, 6);
        int before = match.remainingCount(P1);
        var outcome = new ShotOutcome(ShotResult.POT_TWO, 9);
        var result = rulesEngine.process(match, P1,outcome);

        assertTrue(result.matchEnded());
        assertEquals(P2, result.winner());
    }

}
