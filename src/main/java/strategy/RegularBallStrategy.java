package strategy;

import model.Ball;
import model.Team;
import service.PlayerService;
import util.PlayerUtil;
import util.TeamUtil;

public class RegularBallStrategy implements IBallProcessStrategy{
    TeamUtil teamUtil;
    PlayerService playerService;

    public RegularBallStrategy(PlayerService playerService) {
        this.teamUtil = new TeamUtil(playerService);
        this.playerService = playerService;
    }

    @Override
    public void processBall(Ball ball, Team team) {
        team.getTeamScore().setTotalRuns(team.getTeamScore().getTotalRuns() + ball.getRuns());
        team.getTeamScore().setTotalBalls(team.getTeamScore().getTotalBalls() + 1);
        playerService.addRuns(team.getCurrentStriker(), ball.getRuns());
        if(ball.getRuns() % 2 == 1)
            teamUtil.changeStrike(team);
    }
}
