package strategy;

import model.Ball;
import model.Team;

public class NoBallStrategy implements IBallProcessStrategy{
    @Override
    public void processBall(Ball ball, Team team) {
        team.getTeamScore().setTotalExtras(team.getTeamScore().getTotalExtras() + 1);
        team.getTeamScore().setTotalRuns(team.getTeamScore().getTotalRuns() + 1);
    }
}
