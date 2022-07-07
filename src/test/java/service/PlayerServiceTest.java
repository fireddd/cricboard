package service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerServiceTest {

    private PlayerService playerService;

    @Before
    public void setUp() {
        playerService = new PlayerService();
    }

    @Test
    public void addRunsTest() {
        String playerName = "P71";
        playerService.addPlayer(playerName);
        playerService.addRuns(playerService.getPlayer(playerName), 4);
        Assert.assertEquals(4, playerService.getPlayer(playerName).getScore().getScore());
    }
}