package tictactoe.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PositionTranslatorTest {

    private PositionTranslator positionTranslator;

    @Before
    public void setUp() {
        positionTranslator = new PositionTranslator();
    }

    @Test
    public void translate() throws Exception {
        int[] res = {1,1};
        assertArrayEquals(res, positionTranslator.translate(5));
    }

    @Test
    public void translateOutOfBounds() throws Exception {
        assertEquals(null, positionTranslator.translate(10));
    }
}