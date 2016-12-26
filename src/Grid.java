
public class Grid {

    private static final int ROWS = 3;
    private static final int COlS = 3;
    private Cell[][] grid;
    private int emptySpaces;

    public Grid() {
        grid = new Cell[ROWS][COlS];
        emptySpaces = ROWS * COlS;
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COlS; j++) {
                grid[i][j] = new Cell(i,j);
            }
        }
    }
}