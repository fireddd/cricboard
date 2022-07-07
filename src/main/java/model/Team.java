package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class Team {
    List<Player> playerList;
    int nextPlayer;
    String currentStriker;
    String currentNonStriker;
    TeamScore teamScore;

    public Team(){
        this.playerList = new ArrayList<>();
        this.teamScore = new TeamScore();
    }

}
