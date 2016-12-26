import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mridulkumar on 26/12/2016.
 */
public class GridTest {

    Grid grid;

    @Before
    public void init() {
        grid = new Grid();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                grid.setCell(i, i, Content.CROSS);
            }
        }
    }

    @Test
    public void clearGridTest() throws Exception {
        grid.clearGrid();
        assertEquals(grid.getEmptySpaces(), 9);
    }

    @Test
    public void setCell() throws Exception {
        grid.clearGrid();
        for(int i = 0; i < 3; i++) {
            grid.setCell(0, i, Content.NOUGHT);
        }

        assertEquals(grid.getEmptySpaces(), 6);
    }

    @Test
    public void hasWon() throws Exception {
        grid.clearGrid();
        for(int i = 0; i < 3; i++) {
            grid.setCell(0, i, Content.NOUGHT);
        }

        assertEquals(grid.hasWon(Content.NOUGHT), true);
        assertEquals(grid.hasWon(Content.CROSS), false);
    }

}