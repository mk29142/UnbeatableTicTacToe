package tictactoe;

import tictactoe.data.Content;
import tictactoe.data.Grid;
import tictactoe.data.State;
import tictactoe.players.Computer;
import tictactoe.players.ComputerMinMax;
import tictactoe.utils.InputValidator;
import tictactoe.utils.OutputOptions;
import tictactoe.utils.PositionTranslator;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/*
* Combines all the pieces together and runs the game
**/

public class GameEngine {

    private Grid grid;
    private State currState;
    private Content currPlayer;
    private static Scanner in = new Scanner(System.in);
    private static InputValidator inputValidator = new InputValidator(in);
    private static OutputOptions outputOptions = new OutputOptions();
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

        outputOptions.showGameModes();
        inputValidator.clearStreamOfNonIntegers();
        gameMode = in.nextInt();
        gameMode = inputValidator.getNumberInRange(gameMode, 1, 3);
        initEngine(gameMode);
        System.out.println("Game grid before start");
        grid.drawGrid();

        do {
            move(currPlayer, computer1, computer2, gameMode);
            grid.drawGrid();
            updateGameState(currPlayer);

            outputOptions.printWinningPlayer(currState);

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

    private void move(Content currPlayer, Computer computer1, Computer computer2, int gameMode) {

        int row = -1;
        int col = -1;

        while (true) {
            int[] position = gameModeBasedMove(currPlayer, computer1, computer2, gameMode);
            row = position[0];
            col = position[1];
            if(isWithinBounds(row, col) && isCellEmpty(row, col)) {
                grid.setCell(row, col, currPlayer);
                break;
            }
            else {
                outputOptions.invalidMove(row, col);
            }
        }
    }

    private int[] gameModeBasedMove(Content currPlayer, Computer computer1, Computer computer2, int gameMode) {
        if(gameMode == 1) {
            return makeMove(currPlayer, computer1);
            // Human vs Human
        } else if(gameMode == 2) {
            return getUserInput(currPlayer);
            // Computer vs Computer
        } else {
            delayExecution();
            int[] computerMoves = makeMove(currPlayer, computer1, computer2);
            outputOptions.printMadeMove(currPlayer, computerMoves[0], computerMoves[1]);
            return computerMoves;

        }
    }

    private void delayExecution() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            outputOptions.printMadeMove(currPlayer, computerMoves[0], computerMoves[1]);
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
        outputOptions.printUserInputRequest(currPlayer);
        inputValidator.clearStreamOfNonIntegers();
        int pos = in.nextInt();
        pos = inputValidator.getNumberInRange(pos, 1, 9);
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
        outputOptions.printHumanVsHumanLegend();
        firstPlayer = selectWhoStarts();
        currPlayer = (firstPlayer == 0) ? Content.NOUGHT : Content.CROSS;
    }

    // Initilisation function for HvsC game mode
    private void humanVComputerInit() {
        int firstPlayer = -1;
        outputOptions.printHumanVsComputerLegend();
        firstPlayer = selectWhoStarts();
        currPlayer = (firstPlayer == 0) ? Content.NOUGHT : Content.CROSS;
    }

    private int selectWhoStarts() {
        outputOptions.askWhoStarts();
        inputValidator.clearStreamOfNonIntegers();
        int input = in.nextInt();
        input = inputValidator.getNumberInRange(input, 0, 1);
        return input;
    }

    public State getState() {
        return currState;
    }
}
