package util;

import model.BallType;

public class BallUtil {

    public static BallType getBallTypeFromCode(String ballTypeCode) {
        return switch (ballTypeCode) {
            case "Wd" -> BallType.WIDE;
            case "Nb" -> BallType.NO_BALL;
            case "W" -> BallType.WICKET;
            default -> BallType.REGULAR;
        };
    }
}
