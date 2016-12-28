
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

    public void clearGrid() {
        for(int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COlS; j++) {
                grid[i][j].clear();
            }
        }
        emptySpaces = ROWS * COlS;
    }

    public void setCell(int i, int j, Content content) {
        Cell cell = grid[i][j];
        if(cell.equals(Content.EMPTY)) {
            System.out.println("This cell already contains a value, please choose different cell");
            return;
        } else {
            cell.setContent(content);
            emptySpaces--;
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void drawGrid() {
        for(int i=0 ; i< ROWS ; i++) {
            for(int j=0 ; j< COlS ; j++) {
                grid[i][j].drawCell();
                if (j < COlS - 1) System.out.print("|");
            }
            System.out.println();
            if (i < ROWS - 1) {
                System.out.println("-----");
            }
        }
    }

    public int getEmptySpaces() {
        return emptySpaces;
    }

    public boolean isDraw() {
        return emptySpaces == 0;
    }

    public boolean hasWon(Content player) {

        boolean flag = false;

        // check rows
        for(int i = 0; i < ROWS; i++) {
            if(grid[i][0].getContent() == player &&
                    grid[i][1].getContent() == player &&
                    grid[i][2].getContent() == player) {
                flag = true;
                break;
            }
        }

        //check cols
        for(int i = 0; i < COlS; i++) {
            if (grid[0][i].getContent() == player &&
                    grid[1][i].getContent() == player &&
                    grid[2][i].getContent() == player) {
                flag = true;
                break;
            }
        }

        //check diagonal
        if((grid[0][0].getContent() == player &&
                grid[1][1].getContent() == player &&
                grid[2][2].getContent() == player) ||
            (grid[0][2].getContent() == player &&
                grid[1][1].getContent() == player &&
                grid[2][0].getContent() == player)) {

            flag = true;
        }


        return flag;
    }

    public Content getContent(int row, int col) {
        return grid[row][col].getContent();
    }
}