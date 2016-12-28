import java.util.List;

/*
 * This is a more efficient version of the min max algorithm
 * which uses alpha beta pruning. Will be used for computer against computer.
 * One computer will be normal min max while the other will use alpha beta pruning.
**/
public class ComputerFasterMinMax extends Computer {

    public ComputerFasterMinMax(Grid grid) {
        super(grid);
    }

    @Override
    int[] move() {
        int[] result = minimax(2, computerSeed, Integer.MIN_VALUE, Integer.MAX_VALUE);
        // depth, max-turn, alpha, beta
        return new int[] {result[1], result[2]};   // row, col
    }

    private int[] minimax(int depth, Content player, int alpha, int beta) {
        // Generate possible next moves in a list of int[2] of {row, col}.
        List<int[]> nextMoves = generateMoves();

        // computer maximizing while human is minimizing
        int score;
        int bestRow = -1;
        int bestCol = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            // Gameover or depth reached, evaluate score
            score = evaluate();
            return new int[] {score, bestRow, bestCol};
        } else {
            for (int[] move : nextMoves) {
                cells[move[0]][move[1]].setContent(playerSeed);
                if (player.equals(computerSeed)) {  // computer is maximizing
                    score = minimax(depth - 1, playerSeed, alpha, beta)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else {  // human is minimizing
                    score = minimax(depth - 1, computerSeed, alpha, beta)[0];
                    if (score < beta) {
                        beta = score;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                // undo move
                cells[move[0]][move[1]].setContent(Content.EMPTY);
                // cut-off
                if (alpha >= beta) break;
            }
            return new int[] {(player.equals(computerSeed)) ? alpha : beta, bestRow, bestCol};
        }
    }
}
