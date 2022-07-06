package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    Team firstTeam;
    Team secondTeam;
    int numberOfPlayers;
    int numberOfOvers;

}
