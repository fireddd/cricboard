package strategy;

import model.Ball;
import model.BallType;
import service.PlayerService;

public class BallProcessFactory {

    PlayerService playerService;

    public BallProcessFactory(PlayerService playerService) {
        this.playerService = playerService;
    }

    public IBallProcessStrategy getProcessingStrategy(BallType ballType)
    {
        if(ballType == BallType.NO_BALL)
            return new NoBallStrategy();
        else if(ballType == BallType.WICKET)
            return new WicketBallStrategy(playerService);
        else if(ballType == BallType.WIDE)
            return new WideBallStrategy();
        else
            return new RegularBallStrategy(playerService);
    }
}
