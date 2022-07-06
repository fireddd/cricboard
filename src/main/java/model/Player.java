package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    String name;
    PlayerScore score;
    public Player(String name)
    {
        this.name = name;
        this.score = new PlayerScore();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", score=" + score.toString() +
                '}';
    }
}
