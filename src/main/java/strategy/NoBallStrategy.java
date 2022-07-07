package strategy;

import model.Ball;
import model.Team;
import service.TeamService;

public class NoBallStrategy implements IBallProcessStrategy{

    private TeamService teamService;

    public NoBallStrategy(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public void processBall(Ball ball, Integer teamID) {
        Team team = teamService.getTeam(teamID);
        team.getTeamScore().setTotalExtras(team.getTeamScore().getTotalExtras() + 1);
        team.getTeamScore().setTotalRuns(team.getTeamScore().getTotalRuns() + 1);
    }
}
