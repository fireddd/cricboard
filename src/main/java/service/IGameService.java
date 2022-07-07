package service;

import java.util.List;

public interface IGameService {
    void initializeGame(List<String> inputs);

    void playGame(List<String> gameInputs);

    void printGameResults();
}
