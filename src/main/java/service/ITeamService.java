package service;

import model.Team;

public interface ITeamService {

    void addTeam(Integer teamID);

    Team getTeam(Integer teamID);

    void changeStrike(Integer teamID);

    void initializeTeam(Integer teamID, String players);

    String getNextPlayer(Integer teamID);

    void printTeamScore(Integer teamID);

}
