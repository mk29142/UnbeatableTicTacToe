package tictactoe.data;

import org.junit.*;
import tictactoe.data.Cell;
import tictactoe.data.Content;

import static junit.framework.TestCase.assertEquals;

public class CellTest {

    Cell cell;

    @Before
    public void before() {
        cell = new Cell(0,0);
    }

    @Test
    public void CellInitTest() {
        assertEquals(cell.getContent(), Content.EMPTY);
    }

    @Test
    public void CellSetterTest() {
        cell.setContent(Content.CROSS);
        assertEquals(cell.getContent(), Content.CROSS);
    }

}