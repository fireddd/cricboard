package strategy;

import model.Ball;
import model.BallType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.PlayerService;
import service.TeamService;

public class RegularBallStrategyTest {

    private TeamService teamService;
    private PlayerService playerService;

    @Before
    public void setUp() throws Exception {
        this.playerService = new PlayerService();
        this.teamService = new TeamService(playerService);
    }

    @Test
    public void TestRegularStrategy() {
        Integer teamID = 9;
        String players = "P11 P12";
        teamService.initializeTeam(teamID, players);
        RegularBallStrategy noBallStrategy = new RegularBallStrategy(playerService, teamService);
        Ball ball = new Ball(BallType.REGULAR, 4);
        noBallStrategy.processBall(ball, teamID);
        Assert.assertEquals(4, teamService.getTeam(teamID).getTeamScore().getTotalRuns());
        Assert.assertEquals(1, playerService.getPlayer(teamService.getTeam(teamID).getCurrentStriker()).getScore().getFours());
    }
}