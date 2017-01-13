import java.util.Scanner;

/*
* Combines all the pieces together and runs the game
**/

public class GameEngine {

    private Grid grid;
    private State currState;
    private Content currPlayer;

    private static Scanner in = new Scanner(System.in);

    public GameEngine() {
        grid = new Grid();
    }

    public void play() {
        Computer computer1 = new ComputerMinMax(grid);
        computer1.setSeed(Content.CROSS);

        Computer computer2 = new ComputerMinMax(grid);
        computer2.setSeed(Content.NOUGHT);

        int gameMode = -1;

        System.out.println("Please choose gameMode");
        System.out.println("Press 1 for Human Vs Computer " +
                "\nPress 2 for Human Vs Human" +
                "\nPress 3 for Computer Vs Computer");

        gameMode = in.nextInt();

        while (gameMode < 1 || gameMode > 3) {
            System.out.println("Please enter either 1, 2, or 3");
            checkInputForNumber();
            gameMode = in.nextInt();
        }

        initEngine(gameMode);

        System.out.println("Game grid before start");
        grid.drawGrid();

        do {
            playerMove(currPlayer, computer1, computer2, gameMode);
            grid.drawGrid();
            updateGameState(currPlayer);

            printWinningPlayer(currState);

            currPlayer = switchPlayer();
        }

        // keep on playing until one player wins or the game is drawn
        while(currState.equals(State.PLAYING));
    }

    private Content switchPlayer() {
        return (currPlayer.equals(Content.CROSS)) ? Content.NOUGHT : Content.CROSS;
    }

    private void printWinningPlayer(State currState) {
        if(this.currState.equals(State.CROSS_WON)) {
            System.out.println("Player X Won the game");
            System.out.println("=========================");
        }
        else if(this.currState.equals(State.NOUGHT_WON)) {
            System.out.println("Player O Won the game");
            System.out.println("=========================");
        }
        else if(this.currState.equals(State.DRAW)) {
            System.out.println("Game Drawn. Bye!");
            System.out.println("=========================");
        }
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

                //computer's go
                if(currPlayer.equals(Content.CROSS)) {

                    int [] computerMoves = computer1.move();
                    row = computerMoves[0];
                    col = computerMoves[1];
                    System.out.println("");
                    System.out.println("Computer placed its " + currPlayer + " at "+ row + " " + col);

                } else if(currPlayer.equals(Content.NOUGHT)) {
                    //Humans go

                    int[] res = getUserInput(currPlayer);
                    row = res[0];
                    col = res[1];
                }

               // Human vs Human
            } else if(gameMode == 2) {

                int[] res = getUserInput(currPlayer);
                row = res[0];
                col = res[1];

              // Computer vs Computer
            } else if(gameMode == 3) {

                int [] computerMoves;

                if(currPlayer.equals(Content.CROSS)) {
                    computerMoves = computer1.move();
                } else {
                    computerMoves = computer2.move();
                }

                row = computerMoves[0];
                col = computerMoves[1];
                System.out.println("");
                System.out.println("Computer placed its " + currPlayer + " at "+ row + " " + col);

            }

            if(row >= 0 && row < 3 && col >= 0 && col < 3 && grid.getContent(row, col).equals(Content.EMPTY)) {
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

    private int[] getUserInput(Content currPlayer) {
        System.out.println("");
        System.out.println("Player O please enter the location where you want to place your " + currPlayer + "\n"
                + "The input should be (row[0-2] , column[0-2]) WITHOUT commas, and ONLY SPACES between two digits");

        int row = in.nextInt();
        int col = in.nextInt();


        System.out.println("");

        return new int[]{row, col};
    }

    private void initEngine(int gameMode) {
        grid.clearGrid();
        currState = State.PLAYING;

        switch (gameMode) {
            case 1:
                humanVComputer();
                break;
            case 2:
                humanVHuman();
                break;
            case 3:
                computerVComputer();
                break;
        }

    }

    // Initilisation function for CvsC game mode
    private void computerVComputer() {

        currPlayer = Content.CROSS;
    }

    // Initilisation function for HvsH game mode
    private void humanVHuman() {

        int firstPlayer = -1;

        System.out.println("The symbol for player 2 is X and your symbol is O");
        firstPlayer = selectWhoStarts();

        currPlayer = (firstPlayer == 0) ? Content.NOUGHT : Content.CROSS;
    }

    // Initilisation function for HvsC game mode
    private void humanVComputer() {

        int firstPlayer = -1;

        System.out.println("The symbol for computer is X and your symbol is O");
        firstPlayer = selectWhoStarts();

        currPlayer = (firstPlayer == 0) ? Content.NOUGHT : Content.CROSS;

    }

    private int selectWhoStarts() {

        System.out.println("Press 0(zero) if you want to play first and 1(one) if you " +
                "\nwant computer to play first");

        checkInputForNumber();

        int input = in.nextInt();

        while (input < 0 || input > 1) {
            System.out.println("Please enter either 1 or 0");
            checkInputForNumber();
            input = in.nextInt();
        }
        return input;
    }

    private void checkInputForNumber() {
        while (!in.hasNextInt()) {
            System.out.println("That's not a number!");
            in.next();
        }
    }

    public State getState() {
        return currState;
    }
}
