package tictactoe.data;

import org.junit.Before;
import org.junit.Test;
import tictactoe.data.Content;
import tictactoe.data.Grid;

import static org.junit.Assert.*;

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
    public void hasWonTrueTest() throws Exception {
        grid.clearGrid();
        for(int i = 0; i < 3; i++) {
            grid.setCell(0, i, Content.NOUGHT);
        }

        assertEquals(grid.hasWon(Content.NOUGHT), true);
        assertEquals(grid.hasWon(Content.CROSS), false);
    }

    @Test
    public void hasWonFalseTest() {
        grid.clearGrid();
        for(int i = 0; i < 2; i++) {
            grid.setCell(0, i, Content.NOUGHT);
        }
        grid.setCell(0,2, Content.CROSS);

        assertEquals(grid.hasWon(Content.CROSS), false);

    }

}