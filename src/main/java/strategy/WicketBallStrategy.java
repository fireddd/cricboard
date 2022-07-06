package strategy;

import model.Ball;
import model.Team;
import util.TeamUtil;

public class WicketBallStrategy implements IBallProcessStrategy{
    TeamUtil teamUtil;

    public WicketBallStrategy(){
        this.teamUtil = new TeamUtil();
    }
    @Override
    public void processBall(Ball ball, Team team) {
        team.getTeamScore().setTotalWickets(team.getTeamScore().getTotalWickets() + 1);
        team.getCurrentStriker().getScore().setBalls(team.getCurrentStriker().getScore().getBalls() + 1);
        team.getTeamScore().setTotalBalls(team.getTeamScore().getTotalBalls() + 1);
        processWicket(team);
    }

    private void processWicket(Team team) {
        if(team.getNextPlayer() < team.getPlayerList().size())
            team.setCurrentStriker(teamUtil.getNextPlayer(team));
        team.setNextPlayer(team.getNextPlayer() + 1);
    }
}
