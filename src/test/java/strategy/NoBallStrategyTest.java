package strategy;

import model.Ball;
import model.BallType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.PlayerService;
import service.TeamService;

public class NoBallStrategyTest {

    private TeamService teamService;
    private PlayerService playerService;

    @Before
    public void setUp() throws Exception {
        this.playerService = new PlayerService();
        this.teamService = new TeamService(playerService);
    }

    @Test
    public void TestNoBallStrategy() {
        Integer teamID = 122221;
        String players = "P321 P322";
        teamService.initializeTeam(teamID, players);
        NoBallStrategy noBallStrategy = new NoBallStrategy(teamService);
        Ball ball = new Ball(BallType.NO_BALL);
        noBallStrategy.processBall(ball, teamID);
        Assert.assertEquals(1, teamService.getTeam(teamID).getTeamScore().getTotalRuns());
    }


}
