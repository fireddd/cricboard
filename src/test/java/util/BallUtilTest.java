package util;

import model.BallType;
import org.junit.Assert;
import org.junit.Test;

public class BallUtilTest {

    @Test
    public void testGetBallType() {
        Assert.assertEquals(BallType.WICKET, BallUtil.getBallTypeFromCode("W"));
    }
}
