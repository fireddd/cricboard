package strategy;

import model.Ball;
import model.BallType;

public class BallProcessFactory {

    public IBallProcessStrategy getProcessingStrategy(BallType ballType)
    {
        if(ballType == BallType.NO_BALL)
            return new NoBallStrategy();
        else if(ballType == BallType.WICKET)
            return new WicketBallStrategy();
        else if(ballType == BallType.WIDE)
            return new WideBallStrategy();
        else
            return new RegularBallStrategy();
    }
}
