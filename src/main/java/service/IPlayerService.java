package service;

import model.Player;

public interface IPlayerService {

    void addPlayer(String playerName);

    Player getPlayer(String playerName);

    void addRuns(String playerName, int runs);
}
