package com.micropool.matchservice.rules;
import com.micropool.matchservice.model.Match;
import com.micropool.matchservice.model.ShotOutcome;
import org.springframework.stereotype.Component;

@Component
public class DefaultMatchRulesEngine implements MatchRulesEngine {

    @Override
    public ShotProcessingResult process(Match match, String player, ShotOutcome outcome) {

        // Rule 1: cue ball potted 
        if (outcome.isFoul()) {
            match.switchTurn();
            return new ShotProcessingResult(true, 0, true, false, null);
        }

        // Rule 2: miss 
        if (outcome.isMiss()) {
            match.switchTurn();
            return new ShotProcessingResult(false, 0, true, false, null);
        }

        // Rule 3: legal pot
        int potted = outcome.ballsPotted();
        int remainingBefore = match.remainingCount(player);
        String opponent = match.opponentOf(player);

        // Illegal early 8-ball: 
        if (remainingBefore > 0 && potted > remainingBefore) {
            match.removeBalls(player, remainingBefore);
            match.setEightBallPotted();
            match.completeWith(opponent);
            return new ShotProcessingResult(false, potted, false, true, opponent);
        }

        boolean groupClearBefore = remainingBefore == 0;

        if (groupClearBefore && potted > 0) {
            // Group was already clear 
            match.setEightBallPotted();
            match.completeWith(player);
            return new ShotProcessingResult(false, potted, false, true, player);
        }

        // Normal legal pot
        match.removeBalls(player, potted);
        return new ShotProcessingResult(false, potted, false, false, null);
    }
}
