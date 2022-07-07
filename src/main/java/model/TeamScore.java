package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamScore {
    int totalRuns;
    int totalBalls;
    int totalWickets;
    int totalExtras;

    public TeamScore() {
        this.totalRuns = 0;
        this.totalBalls = 0;
        this.totalWickets = 0;
        this.totalExtras = 0;
    }

    @Override
    public String toString() {
        return "TeamScore{" +
                "totalRuns=" + totalRuns +
                ", totalBalls=" + totalBalls +
                ", totalWickets=" + totalWickets +
                ", totalExtras=" + totalExtras +
                '}';
    }
}
