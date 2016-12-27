import java.util.ArrayList;
import java.util.List;

public class ComputerMinMax extends Computer {

    private Cell[][] grid;

    public ComputerMinMax(Grid grid) {
        super(grid);
    }

    /** Get next best move for computer. Returns {row, col} */
    @Override
    int[] move() {
        int[] result = minimax(2, computerSeed); // depth, max turn
        return new int[] {result[1], result[2]};
    }

    /** Recursive minimax at level of depth for either maximizing or minimizing player.
            Returns {score, row, col}  */
    private int[] minimax(int depth, Content player) {

        List<int[]> nextMoves = generateMoves();
        int bestScore = (player == computerSeed) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            // Gameover or depth reached, evaluate score
            bestScore = evaluate();
        } else {
            for (int[] m : nextMoves) {
                // Try this move for the current "player"
                cells[m[0]][m[1]].setContent(player);
                if (player == computerSeed) {  // computer is maximizing player
                    currentScore = minimax(depth - 1, playerSeed)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = m[0];
                        bestCol = m[1];
                    }
                } else {  // human/player is minimizing player
                    currentScore = minimax(depth - 1, playerSeed)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = m[0];
                        bestCol = m[1];
                    }
                }
                // Undo move
                cells[m[0]][m[1]].setContent(Content.EMPTY);
            }
        }
        return new int[] {bestScore, bestRow, bestCol};
    }

    private List<int[]> generateMoves() {

        List<int[]> moves = new ArrayList<int[]>();

        // If gameover, i.e., no next move
        if (hasWon(computerSeed) || hasWon(playerSeed)) {
            return moves;   // return empty list
        }

        // Search for empty cells and add to the List
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].getContent() == Content.EMPTY) {
                    moves.add(new int[] {row, col});
                }
            }
        }
        return moves;

    }

    private int evaluate() {
        int score = 0;
        // evaluateLines(int row1, int col1, int row2, int col2, int row3, int col3)
        score+=evaluateLines(0,0,0,1,0,2); // first row
        score+=evaluateLines(1,0,1,1,1,2); // second row
        score+=evaluateLines(2,0,2,1,2,2); // third row
        score+=evaluateLines(0,0,1,0,2,0); // first column
        score+=evaluateLines(0,1,1,1,2,1); // second column
        score+=evaluateLines(0,2,1,2,2,2); // third column
        score+=evaluateLines(0,0,1,1,2,2); // \ diagonal
        score+=evaluateLines(0,2,1,1,2,0);// / diagonal

        return score;
    }

    /** The heuristic evaluation function for the given line of 3 cells
     Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
     -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
     0 otherwise */
    private int evaluateLines(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;

        // First cell
        if (cells[row1][col1].getContent() == computerSeed) {
            score = 1;
        } else if (cells[row1][col1].getContent() == playerSeed) {
            score = -1;
        }

        // Second cell
        if (cells[row2][col2].getContent() == computerSeed) {
            if (score == 1) {   // cell1 is computerSeed
                score = 10;
            } else if (score == -1) {  // cell1 is playerSeed
                return 0;
            } else {  // cell1 is empty
                score = 1;
            }
        } else if (cells[row2][col2].getContent() == playerSeed) {
            if (score == -1) { // cell1 is computerSeed
                score = -10;
            } else if (score == 1) { // cell1 is playerSeed
                return 0;
            } else {  // cell1 is empty
                score = -1;
            }
        }

        // Third cell
        if (cells[row3][col3].getContent() == computerSeed) {
            if (score > 0) {  // cell1 and/or cell2 is mySeed
                score *= 10;
            } else if (score < 0) {  // cell1 and/or cell2 is oppSeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = 1;
            }
        } else if (cells[row3][col3].getContent() == playerSeed) {
            if (score < 0) {  // cell1 and/or cell2 is oppSeed
                score *= 10;
            } else if (score > 1) {  // cell1 and/or cell2 is mySeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = -1;
            }
        }
        return score;

    }

    private boolean hasWon(Content player) {

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
        for(int i = 0; i < COLS; i++) {
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
}
