package strategy;

import model.Ball;

public interface IBallProcessStrategy {
    void processBall(Ball ball, Integer teamID);
}
