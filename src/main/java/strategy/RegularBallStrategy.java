package strategy;

import model.Ball;
import model.Team;
import util.PlayerUtil;
import util.TeamUtil;

public class RegularBallStrategy implements IBallProcessStrategy{
    TeamUtil teamUtil;
    PlayerUtil playerUtil;

    public RegularBallStrategy() {
        this.teamUtil = new TeamUtil();
        this.playerUtil = new PlayerUtil();
    }

    @Override
    public void processBall(Ball ball, Team team) {
        team.getTeamScore().setTotalRuns(team.getTeamScore().getTotalRuns() + ball.getRuns());
        team.getTeamScore().setTotalBalls(team.getTeamScore().getTotalBalls() + 1);
        playerUtil.addRuns(team.getCurrentStriker(), ball.getRuns());
        if(ball.getRuns() % 2 == 1)
            teamUtil.changeStrike(team);
    }
}
