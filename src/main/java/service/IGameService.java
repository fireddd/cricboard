package service;

import java.util.List;

public interface IGameService {
    void initializeGame(String numberOfPlayers, String numberOfOvers);

    void playGame(List<String> gameInputs);

    void printGameResults();
}
