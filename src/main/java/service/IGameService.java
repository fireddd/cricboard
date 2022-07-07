package service;

import java.util.List;

public interface IGameService {
    void initializeGame(String numberOfPlayers, String numberOfOvers);

    void playGame(List<String> gameInputs, Integer teamOneID, Integer teamTwoID);

    void printGameResults(Integer teamOneID, Integer teamTwoID);
}
