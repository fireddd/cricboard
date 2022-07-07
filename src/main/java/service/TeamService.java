package service;

import dao.TeamDao;
import exceptions.TeamExistsException;
import exceptions.TeamNotFoundException;
import model.Team;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TeamService implements ITeamService {

    private TeamDao teamDao;
    private PlayerService playerService;

    public TeamService(PlayerService playerService) {
        this.playerService = playerService;
        this.teamDao = TeamDao.getInstance();
    }

    @Override
    public void addTeam(Integer teamID) {
        try {
            this.teamDao.addTeam(teamID);
        } catch (Exception e) {
            throw new TeamExistsException();
        }

    }

    @Override
    public Team getTeam(Integer teamID) {
        try {
            return this.teamDao.getTeam(teamID);
        } catch (Exception e) {
            throw new TeamNotFoundException();
        }

    }

    @Override
    public void changeStrike(Integer teamID) {
        Team team = getTeam(teamID);
        String striker = team.getCurrentStriker();
        team.setCurrentStriker(team.getCurrentNonStriker());
        team.setCurrentNonStriker(striker);
    }

    @Override
    public void initializeTeam(Integer teamID, String players) {
        addTeam(teamID);
        Team team = getTeam(teamID);
        List<String> stringPlayers = Arrays.stream(players.trim().split(" ")).collect(Collectors.toList());
        for (String player : stringPlayers) {
            team.getPlayerList().add(player);
            this.playerService.addPlayer(player);
        }
        team.setCurrentStriker(team.getPlayerList().get(0));
        team.setCurrentNonStriker(team.getPlayerList().get(1));
        team.setNextPlayer(2);
    }

    @Override
    public String getNextPlayer(Integer teamID) {
        Team team = getTeam(teamID);
        return team.getPlayerList().get(team.getNextPlayer());
    }

    private void printIndividualScore(Integer teamID) {
        Team team = getTeam(teamID);
        for (String currentPlayer : team.getPlayerList()) {
            System.out.println(this.playerService.getPlayer(currentPlayer).toString());
        }
    }

    @Override
    public void printTeamScore(Integer teamID) {
        Team team = getTeam(teamID);
        System.out.println(team.getTeamScore().toString());
        printIndividualScore(teamID);
    }


}
