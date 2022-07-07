package strategy;

import model.Ball;
import model.Team;
import service.PlayerService;
import util.TeamUtil;

public class WicketBallStrategy implements IBallProcessStrategy{
    TeamUtil teamUtil;
    PlayerService playerService;

    public WicketBallStrategy(PlayerService playerService){
        this.teamUtil = new TeamUtil(playerService);
        this.playerService = playerService;
    }

    @Override
    public void processBall(Ball ball, Team team) {
        team.getTeamScore().setTotalWickets(team.getTeamScore().getTotalWickets() + 1);
        playerService.getPlayer(team.getCurrentStriker()).getScore().setBalls(playerService.getPlayer(team.getCurrentStriker()).getScore().getBalls() + 1);
        //team.getCurrentStriker().getScore().setBalls(team.getCurrentStriker().getScore().getBalls() + 1);
        team.getTeamScore().setTotalBalls(team.getTeamScore().getTotalBalls() + 1);
        processWicket(team);
    }

    private void processWicket(Team team) {
        if(team.getNextPlayer() < team.getPlayerList().size())
            team.setCurrentStriker(teamUtil.getNextPlayer(team).getName());
        team.setNextPlayer(team.getNextPlayer() + 1);
    }
}
