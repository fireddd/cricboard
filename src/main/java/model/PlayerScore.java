package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerScore {
    int score;
    int balls;
    int fours;
    int sixes;
    public PlayerScore()
    {
        this.score = 0;
        this.balls = 0;
        this.fours = 0;
        this.sixes = 0;
    }

    @Override
    public String toString() {
        return "{" +
                "score=" + score +
                ", balls=" + balls +
                ", fours=" + fours +
                ", sixes=" + sixes +
                '}';
    }
}
