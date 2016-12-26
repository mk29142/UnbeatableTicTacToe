import org.junit.Test;
import static org.junit.Assert.*;

public class CellTest {

    @Test
    public void CellTest() {
        Cell cell = new Cell(0,0);
        assertEquals(cell.getContent(), Content.EMPTY);
    }

}