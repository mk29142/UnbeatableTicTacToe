package tictactoe;

import tictactoe.data.Content;
import tictactoe.data.Grid;
import tictactoe.data.State;
import tictactoe.players.Computer;
import tictactoe.players.ComputerMinMax;
import tictactoe.utils.NumberValidator;
import tictactoe.utils.Options;
import tictactoe.utils.PositionTranslator;

import java.util.Scanner;


/*
* Combines all the pieces together and runs the game
**/

public class GameEngine {

    private Grid grid;
    private State currState;
    private Content currPlayer;
    private static Scanner in = new Scanner(System.in);
    private static NumberValidator numberValidator = new NumberValidator(in);
    private static Options options = new Options();
    private static PositionTranslator positionTranslator = new PositionTranslator();

    public GameEngine() {
        grid = new Grid();
    }

    public void play() {
        Computer computer1 = new ComputerMinMax(grid);
        computer1.setSeed(Content.CROSS);

        Computer computer2 = new ComputerMinMax(grid);
        computer2.setSeed(Content.NOUGHT);

        int gameMode = -1;

        options.showGameModes();

        numberValidator.clearStreamOfNonNumbers();

        gameMode = in.nextInt();

        gameMode = numberValidator.getNumberInRange(gameMode, 1, 3);

        initEngine(gameMode);

        System.out.println("Game grid before start");
        grid.drawGrid();

        do {
            playerMove(currPlayer, computer1, computer2, gameMode);
            grid.drawGrid();
            updateGameState(currPlayer);

            options.printWinningPlayer(currState);

            currPlayer = switchPlayer();
        }

        // keep on playing until one player wins or the game is drawn
        while(currState.equals(State.PLAYING));
    }

    private Content switchPlayer() {
        return (currPlayer.equals(Content.CROSS)) ? Content.NOUGHT : Content.CROSS;
    }

    private void updateGameState(Content currPlayer) {
        if (grid.hasWon(currPlayer)) {
            if (currPlayer.equals(Content.CROSS))
                currState = State.CROSS_WON;
            if (currPlayer.equals(Content.NOUGHT))
                currState = State.NOUGHT_WON;
        } else if (grid.isDraw()) {
            currState = State.DRAW;
        }
    }

    private void playerMove(Content currPlayer, Computer computer1, Computer computer2, int gameMode) {

        int row = -1;
        int col = -1;

        while (true) {
            // Human vs Computer
            if(gameMode == 1) {
                int[] moves = makeMove(currPlayer, computer1);
                row = moves[0];
                col = moves[1];

               // Human vs Human
            } else if(gameMode == 2) {
                int[] res = getUserInput(currPlayer);
                row = res[0];
                col = res[1];

              // Computer vs Computer
            } else if(gameMode == 3) {
                int[] computerMoves = makeMove(currPlayer, computer1, computer2);
                row = computerMoves[0];
                col = computerMoves[1];
                System.out.println("");
                System.out.println("Computer placed its " + currPlayer + " at "+ row + " " + col);

            }
            if(isWithinBounds(row, col) && isCellEmpty(row, col)) {
                grid.setCell(row, col, currPlayer);
                break;
            }
            else {
                System.out.println("");
                System.out.println("This move at (" + row + "," + col
                        + ") is not valid. Try again...");
            }
        }
    }

    private int[] makeMove(Content currPlayer, Computer computer1, Computer computer2) {
        int[] computerMoves;
        if(currPlayer.equals(Content.CROSS)) {
            computerMoves = computer1.move();
        } else {
            computerMoves = computer2.move();
        }
        return computerMoves;
    }

    private int[] makeMove(Content currPlayer, Computer computer) {
        if(currPlayer.equals(Content.CROSS)) {
            int [] computerMoves = computer.move();
            System.out.println("");
            System.out.println("Computer placed its " + currPlayer + " at "+ computerMoves[0] + " " + computerMoves[1]);
            return computerMoves;
        } else {
            //Humans go
            return  getUserInput(currPlayer);
        }
    }

    private boolean isCellEmpty(int row, int col) {
        return grid.getContent(row, col).equals(Content.EMPTY);
    }

    private boolean isWithinBounds(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }

    private int[] getUserInput(Content currPlayer) {
        System.out.println("");
        System.out.println("Player O please enter the location where you want to place your " + currPlayer + "\n"
                + "The input should be between 1 and 9 inclusive.");

        numberValidator.clearStreamOfNonNumbers();

        int pos = in.nextInt();

        pos = numberValidator.getNumberInRange(pos, 1, 9);

        System.out.println("");

        return positionTranslator.translate(pos);
    }

    private void initEngine(int gameMode) {
        grid.clearGrid();
        currState = State.PLAYING;

        switch (gameMode) {
            case 1:
                humanVComputerInit();
                break;
            case 2:
                humanVHumanInit();
                break;
            case 3:
                computerVComputerInit();
                break;
        }
    }

    // Initilisation function for CvsC game mode
    private void computerVComputerInit() {

        currPlayer = Content.CROSS;
    }

    // Initilisation function for HvsH game mode
    private void humanVHumanInit() {

        int firstPlayer = -1;

        System.out.println("\nThe symbol for player 2 is X and your symbol is O\n");
        firstPlayer = selectWhoStarts();

        currPlayer = (firstPlayer == 0) ? Content.NOUGHT : Content.CROSS;
    }

    // Initilisation function for HvsC game mode
    private void humanVComputerInit() {

        int firstPlayer = -1;

        System.out.println("\nThe symbol for computer is X and your symbol is O\n");
        firstPlayer = selectWhoStarts();

        currPlayer = (firstPlayer == 0) ? Content.NOUGHT : Content.CROSS;

    }

    private int selectWhoStarts() {

        System.out.println("Press 0(zero) if you want to play first and 1(one) if you " +
                "\nwant computer to play first");

        numberValidator.clearStreamOfNonNumbers();

        int input = in.nextInt();

        input = numberValidator.getNumberInRange(input, 0, 1);

        return input;
    }

    public State getState() {
        return currState;
    }
}
