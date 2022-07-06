package util;

import model.BallType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BallUtilTest {

    BallUtil ballUtil;
    @Before
    public void setUp() throws Exception {
        ballUtil = new BallUtil();
    }

    @Test
    public void testGetBallType(){
        Assert.assertEquals(BallType.WICKET, ballUtil.getBallTypeFromCode("W"));
    }
}