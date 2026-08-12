import com.micropool.matchservice.model.Match;
import com.micropool.matchservice.model.ShotOutcome;

public class DefaultMatchRulesEngine implements MatchRulesEngine{

    public ShotProcessingResult process(Match match, String player, ShotOutcome outcome){
        boolean groupClear = false;

        //Rule 1 cue ball potted
        if(outcome.isFoul()){
            match.switchTurn();
            return new ShotProcessingResult(true, 0, true, false, player);
        
        }

        //Rule 2 miss
        if(outcome.isMiss()){
            match.switchTurn();
            return new ShotProcessingResult(false, 0, true, false, player);
        }

        //Rule 3 pot
        if(outcome.isPot()){
            int balls = outcome.ballsPotted();
            int remainingBalls = match.remainingCount(player);
            if (remainingBalls = 0){
                groupClear = true;
            }
            if (groupClear && potted > 0) {
              match.markEightBallPotted();
              match.completeWith(player);
              return new ShotProcessingResult(false, potted, false, true, player);
          }

        //   if (!groupClear && potted > match.remainingCount(player)) {
           
        //     match.markEightBallPotted();
        //     match.completeWith(opponent); // illegal early 8-ball -> opponent wins
        //     return new ShotProcessingResult(false, potted, false, true, opponent);
        //  }
          return new ShotProcessingResult(false, potted, false, false, null);
        }

      



    }
    
}
