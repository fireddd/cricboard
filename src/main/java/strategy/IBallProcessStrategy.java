package strategy;

import model.Ball;
import model.Team;
import model.TeamScore;

public interface IBallProcessStrategy {
    void processBall(Ball ball, Team team);
}
