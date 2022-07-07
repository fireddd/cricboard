package service;

import model.Team;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TeamServiceTest {

    private PlayerService playerService;

    @Before
    public void setUp() throws Exception {
        this.playerService = new PlayerService();
    }

    @Test
    public void changeStrikeTest() {
        TeamService teamService = new TeamService(this.playerService);
        Integer teamID = 1;
        String players = "P1 P2";
        teamService.initializeTeam(teamID, players);
        Team team = teamService.getTeam(teamID);
        Assert.assertEquals("P1", team.getCurrentStriker());
        Assert.assertEquals("P2", team.getCurrentNonStriker());
        teamService.changeStrike(teamID);
        Assert.assertEquals("P2", team.getCurrentStriker());
        Assert.assertEquals("P1", team.getCurrentNonStriker());
    }
}