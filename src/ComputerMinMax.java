import java.util.ArrayList;
import java.util.List;

public class ComputerMinMax extends Computer {

    public ComputerMinMax(Grid grid) {
        super(grid);
    }

    /**
     * Get next best move for computer.
     **/
    @Override
    int[] move() {
        int[] result = minimax(2, computerSeed); // depth, max turn
        return new int[]{result[1], result[2]};
    }

    /**
     * Recursive minimax at level of depth for either maximizing or minimizing player.
     **/
    private int[] minimax(int depth, Content player) {

        List<int[]> nextMoves = generateMoves();

        // Computer is maximizing while the player will be minimizing
        int bestScore = (player.equals(computerSeed)) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
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
                if (player.equals(computerSeed)) {  // computer is maximizing
                    currentScore = minimax(depth - 1, playerSeed)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = m[0];
                        bestCol = m[1];
                    }
                } else {  // human/player is minimizing
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
        return new int[]{bestScore, bestRow, bestCol};
    }
}

