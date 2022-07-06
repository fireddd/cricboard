package util;

import model.Player;
import model.Team;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TeamUtil {
    PlayerUtil playerUtil;

    public TeamUtil(){
        playerUtil = new PlayerUtil();
    }
    public void changeStrike(Team team)
    {
        Player striker = team.getCurrentStriker();
        team.setCurrentStriker(team.getCurrentNonStriker());
        team.setCurrentNonStriker(striker);
    }

    public Team initializeTeam(String players) {
        Team team = new Team();
        List<String> stringPlayers = Arrays.stream(players.trim().split(" ")).collect(Collectors.toList());
        for(String player: stringPlayers)
            team.getPlayerList().add(new Player(player));
        team.setCurrentStriker(team.getPlayerList().get(0));
        team.setCurrentNonStriker(team.getPlayerList().get(1));
        team.setNextPlayer(2);
        return team;
    }

    public  Player getNextPlayer(Team team)
    {
        return team.getPlayerList().get(team.getNextPlayer());
    }

    public void printIndividualScore(Team team)
    {
        for(Player currentPlayer: team.getPlayerList())
        {
            System.out.println(currentPlayer.toString());
        }
    }

    public void printTeamScore(Team team)
    {
        System.out.println(team.getTeamScore().toString());
        printIndividualScore(team);
    }
}
