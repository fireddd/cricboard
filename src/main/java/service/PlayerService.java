package service;

import dao.PlayerDao;
import exceptions.PlayerExistsException;
import exceptions.PlayerNotFoundException;
import model.Player;

public class PlayerService implements IPlayerService{

    private PlayerDao playerDao;

    public PlayerService() {
        this.playerDao = PlayerDao.getInstance();
    }

    @Override
    public void addPlayer(String playerName) {
        try{
            this.playerDao.addPlayer(playerName);
        }
        catch (PlayerExistsException e)
        {
            System.out.println("Player alreadys exists with the name" + playerName);
        }
    }

    @Override
    public Player getPlayer(String playerName) {
        try {
            return this.playerDao.getPlayer(playerName);
        }
        catch(PlayerNotFoundException exception){
            throw exception;
        }

    }

    @Override
    public void addRuns(String playerName, int runs) {
        Player player = getPlayer(playerName);
        addRuns(player, runs);
        this.playerDao.addPlayer(playerName, player);
    }


    public void addRuns(Player player, Integer runs)
    {
        if(runs%4 == 0)
            player.getScore().setFours(player.getScore().getFours() + 1);
        else if(runs%6 == 0)
            player.getScore().setSixes(player.getScore().getSixes() + 1);
        player.getScore().setScore(player.getScore().getScore() + runs);
        player.getScore().setBalls(player.getScore().getBalls() + 1);
    }

}
