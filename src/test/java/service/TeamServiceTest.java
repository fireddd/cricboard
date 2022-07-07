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
        Integer teamID = 1111;
        String players = "P1111 P1112";
        teamService.initializeTeam(teamID, players);
        Team team = teamService.getTeam(teamID);
        Assert.assertEquals("P1111", team.getCurrentStriker());
        Assert.assertEquals("P1112", team.getCurrentNonStriker());
        teamService.changeStrike(teamID);
        Assert.assertEquals("P1112", team.getCurrentStriker());
        Assert.assertEquals("P1111", team.getCurrentNonStriker());
    }
}