package util;

import model.Player;

public class PlayerUtil {

//    public void wicket(Player player)
//    {
//        player.getScore().setBalls(player.getScore().getBalls()+1);
//    }

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
