package strategy;

import model.Ball;
import model.BallType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.PlayerService;
import service.TeamService;

public class WicketBallStrategyTest {

    private TeamService teamService;
    private PlayerService playerService;

    @Before
    public void setUp() throws Exception {
        this.playerService = new PlayerService();
        this.teamService = new TeamService(playerService);
    }

    @Test
    public void TestWicketStrategy() {
        Integer teamID = 3;
        String players = "P61 P62";
        teamService.initializeTeam(teamID, players);
        WicketBallStrategy wicketBallStrategy = new WicketBallStrategy(playerService, teamService);
        Ball ball = new Ball(BallType.WICKET);
        wicketBallStrategy.processBall(ball, teamID);
        Assert.assertEquals(0, teamService.getTeam(teamID).getTeamScore().getTotalRuns());
        Assert.assertEquals(1, teamService.getTeam(teamID).getTeamScore().getTotalWickets());
    }
}