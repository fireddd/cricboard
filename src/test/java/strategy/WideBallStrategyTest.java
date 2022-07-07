package strategy;

import model.Ball;
import model.BallType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.PlayerService;
import service.TeamService;


public class WideBallStrategyTest {

    private TeamService teamService;

    @Before
    public void setUp() {
        PlayerService playerService = new PlayerService();
        this.teamService = new TeamService(playerService);
    }


    @Test
    public void TestWideBallStrategy() {
        Integer teamID = 642414;
        String players = "P84241 P84212";
        teamService.initializeTeam(teamID, players);
        WideBallStrategy wideBallStrategy = new WideBallStrategy(teamService);
        Ball ball = new Ball(BallType.WIDE);
        wideBallStrategy.processBall(ball, teamID);
        Assert.assertEquals(1, teamService.getTeam(teamID).getTeamScore().getTotalRuns());
    }

}