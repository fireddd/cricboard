package strategy;

import model.Ball;
import model.Team;
import service.PlayerService;
import service.TeamService;

public class RegularBallStrategy implements IBallProcessStrategy{
    TeamService teamService;
    PlayerService playerService;

    public RegularBallStrategy( PlayerService playerService, TeamService teamService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    public void processBall(Ball ball, Integer teamID) {
        Team team = teamService.getTeam(teamID);
        team.getTeamScore().setTotalRuns(team.getTeamScore().getTotalRuns() + ball.getRuns());
        team.getTeamScore().setTotalBalls(team.getTeamScore().getTotalBalls() + 1);
        playerService.addRuns(team.getCurrentStriker(), ball.getRuns());
        if(ball.getRuns() % 2 == 1)
            teamService.changeStrike(teamID);
    }
}
