package strategy;

import model.Ball;
import model.BallType;
import service.PlayerService;
import service.TeamService;

public class BallProcessFactory {

    private PlayerService playerService;
    private TeamService teamService;
    public BallProcessFactory(PlayerService playerService, TeamService teamService) {
        this.playerService = playerService;
        this.teamService = teamService;
    }

    public IBallProcessStrategy getProcessingStrategy(BallType ballType)
    {
        if(ballType == BallType.NO_BALL)
            return new NoBallStrategy(teamService);
        else if(ballType == BallType.WICKET)
            return new WicketBallStrategy(playerService, teamService);
        else if(ballType == BallType.WIDE)
            return new WideBallStrategy(teamService);
        else
            return new RegularBallStrategy(playerService, teamService);
    }
}
