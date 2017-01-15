package tictactoe.utils;

import java.util.HashMap;
import java.util.Map;

public class PositionTranslator {

    private Map<Integer, int[]> positions;

    public PositionTranslator() {
        positions = new HashMap<>();
        init();
    }

    private void init() {

        int counter = 1;

        for(int row = 0; row < 3; row++) {
            for(int cols = 0; cols < 3; cols++) {
                positions.put(counter, new int[]{row, cols});
                counter++;
            }
        }
    }

    public int[] translate(int i) {
        return positions.get(i);
    }
}
