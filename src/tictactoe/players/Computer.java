package tictactoe.players;

import tictactoe.data.Cell;
import tictactoe.data.Content;
import tictactoe.data.Grid;

import java.util.ArrayList;
import java.util.List;

public abstract class Computer {

    protected int ROWS = 3;
    protected int COLS = 3;

    protected Cell[][] cells;
    protected Content computerSeed;    // computer's symbol
    protected Content playerSeed;   // Players's symbol

    public Computer(Grid grid) {
        this.cells = grid.getGrid();
    }

    /**
     * Sets symbol for the computer and player
     * */
    public void setSeed(Content content) {
        this.computerSeed = content;
        this.playerSeed = (content.equals(Content.CROSS)) ? Content.NOUGHT : Content.CROSS;
    }

    /**
     * Returns a list off all possible next moves
     **/
    protected List<int[]> generateMoves() {

        List<int[]> moves = new ArrayList<int[]>();

        // If gameover, i.e., no next move
        if (hasWon(computerSeed) || hasWon(playerSeed)) {
            return moves;   // return empty list
        }

        // Search for empty cells and add to the List
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].getContent().equals(Content.EMPTY)) {
                    moves.add(new int[] {row, col});
                }
            }
        }
        return moves;

    }
    protected int evaluate() {
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

    /**
     Evalution for given line of 3 cells, +100, + 10, +1 for a computer
     -100, -10, -1 for a human or oponent.
     **/
    private int evaluateLines(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;

        // First cell
        if (cells[row1][col1].getContent().equals(computerSeed)) {
            score = 1;
        } else if (cells[row1][col1].getContent().equals(playerSeed)) {
            score = -1;
        }

        // Second cell
        if (cells[row2][col2].getContent().equals(computerSeed)) {
            if (score == 1) {   // cell1 is computerSeed
                score = 10;
            } else if (score == -1) {  // cell1 is playerSeed
                return 0;
            } else {  // cell1 is empty
                score = 1;
            }
        } else if (cells[row2][col2].getContent().equals(playerSeed)) {
            if (score == -1) { // cell1 is computerSeed
                score = -10;
            } else if (score == 1) { // cell1 is playerSeed
                return 0;
            } else {  // cell1 is empty
                score = -1;
            }
        }

        // Third cell
        if (cells[row3][col3].getContent().equals(computerSeed)) {
            if (score > 0) {  // cell1 and/or cell2 is computerSeed
                score *= 10;
            } else if (score < 0) {  // cell1 and/or cell2 is playerSeed
                return 0;
            } else {  // cell1 and cell2 are empty
                score = 1;
            }
        } else if (cells[row3][col3].getContent().equals(playerSeed)) {
            if (score < 0) {  // cell1 and/or cell2 is playerSeed
                score *= 10;
            } else if (score > 1) {  // cell1 and/or cell2 is computerSeed
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
        for(int i = 0; i < 3; i++) {
            if(cells[i][0].getContent().equals(player) &&
                    cells[i][1].getContent().equals(player) &&
                    cells[i][2].getContent().equals(player)) {
                flag = true;
                break;
            }
        }

        //check cols
        for(int i = 0; i < 3; i++) {
            if (cells[0][i].getContent().equals(player) &&
                    cells[1][i].getContent().equals(player) &&
                    cells[2][i].getContent().equals(player)) {
                flag = true;
                break;
            }
        }

        //check diagonal
        if((cells[0][0].getContent().equals(player) &&
                cells[1][1].getContent().equals(player) &&
                cells[2][2].getContent().equals(player)) ||
                (cells[0][2].getContent().equals(player) &&
                        cells[1][1].getContent().equals(player) &&
                        cells[2][0].getContent().equals(player))) {

            flag = true;
        }
        return flag;
    }

    public abstract int[] move();

}
