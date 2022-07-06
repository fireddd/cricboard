package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum BallType {
    WIDE("Wide", "Wd"),
    NO_BALL("No Ball","Nb"),
    WICKET("Wicket","W"),
    REGULAR("Regular","R");
    String code;
    String name;
}
