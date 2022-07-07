package strategy;

import model.Ball;
import model.Team;
import service.PlayerService;
import service.TeamService;

public class WicketBallStrategy implements IBallProcessStrategy {
    private TeamService teamService;
    private PlayerService playerService;

    public WicketBallStrategy(PlayerService playerService, TeamService teamService) {
        this.teamService = teamService;
        this.playerService = playerService;
    }

    @Override
    public void processBall(Ball ball, Integer teamID) {
        Team team = teamService.getTeam(teamID);
        team.getTeamScore().setTotalWickets(team.getTeamScore().getTotalWickets() + 1);
        playerService.getPlayer(team.getCurrentStriker()).getScore().setBalls(
                playerService.getPlayer(team.getCurrentStriker()).getScore().getBalls() + 1);
        team.getTeamScore().setTotalBalls(team.getTeamScore().getTotalBalls() + 1);
        processWicket(teamID);
    }

    private void processWicket(Integer teamID) {
        Team team = teamService.getTeam(teamID);
        if (team.getNextPlayer() < team.getPlayerList().size())
            team.setCurrentStriker(teamService.getNextPlayer(teamID));
        team.setNextPlayer(team.getNextPlayer() + 1);
    }
}
