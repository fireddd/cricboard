package dao;

import exceptions.OutOfBoundBallException;
import exceptions.PlayerExistsException;
import exceptions.PlayerNotFoundException;
import model.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerDao {
    private static PlayerDao playerDaoInstance;

    private Map<String, Player> playerMap;

    public static PlayerDao getInstance(){
        if(playerDaoInstance == null)
            playerDaoInstance = new PlayerDao();
        return playerDaoInstance;
    }

    private PlayerDao(){
        playerMap = new HashMap<>();
    }

    public void addPlayer(String playerName)
    {
        if(this.playerMap.containsKey(playerName)){
            throw new PlayerExistsException();
        }
        this.playerMap.put(playerName, new Player(playerName));
    }

    public Player getPlayer(String playerName)
    {
        if(!this.playerMap.containsKey(playerName)){
            throw new PlayerNotFoundException();
        }
        return this.playerMap.get(playerName);
    }

}
