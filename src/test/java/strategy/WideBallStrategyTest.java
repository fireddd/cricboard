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
        Integer teamID = 6;
        String players = "P81 P82";
        teamService.initializeTeam(teamID, players);
        WideBallStrategy wideBallStrategy = new WideBallStrategy(teamService);
        Ball ball = new Ball(BallType.WIDE);
        wideBallStrategy.processBall(ball, teamID);
        Assert.assertEquals(1, teamService.getTeam(teamID).getTeamScore().getTotalRuns());
    }

}