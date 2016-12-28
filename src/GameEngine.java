import java.util.Scanner;

public class GameEngine {

    private Grid grid;
    private State currState;
    private Content currPlayer;

    private static Scanner in = new Scanner(System.in);

    public GameEngine() {
        grid = new Grid();
        ComputerMinMax computer = new ComputerMinMax(grid);
        computer.setSeed(Content.CROSS);
        int firstPlayer = -1;

        System.out.println("The symbol for computer is X and your symbol is O");
        System.out.println("Press 0(zero) if you want to play first and 1(one) if you " +
                "\nwant computer to play first");

        firstPlayer = in.nextInt();

        while (firstPlayer < 0 || firstPlayer > 1) {
            System.out.println("Please enter either 1 or 0");
            while (!in.hasNextInt()) {
                System.out.println("That's not a number!");
                in.next();
            }
        }

        System.out.println("Game grid before start");
        grid.drawGrid();

        initEngine(firstPlayer);

        do {
            playerMove(currPlayer, computer);
            grid.drawGrid();
            updateGameState(currPlayer);

            if(currState.equals(State.CROSS_WON)) {
                System.out.println("Player X Won the game");
                System.out.println("=========================");
            }
            else if(currState.equals(State.NOUGHT_WON)) {
                System.out.println("Player O Won the game");
                System.out.println("=========================");
            }
            else if(currState.equals(State.DRAW)) {
                System.out.println("Game Drawn. Bye!");
                System.out.println("=========================");
            }

            if(currPlayer.equals(Content.CROSS))
                currPlayer = Content.NOUGHT;
            else if(currPlayer.equals(Content.NOUGHT))
                currPlayer = Content.CROSS;
        }

        // keep on playing until one player wins or the game is drawn
        while(currState.equals(State.PLAYING));
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

    private void playerMove(Content currPlayer, ComputerMinMax computer) {
        boolean validInput = false;

        int row = -1;
        int col = -1;

        //computer's go
        if(currPlayer.equals(Content.CROSS)) {

            int [] computerMoves = computer.move();
            row = computerMoves[0];
            col = computerMoves[1];
            System.out.println("");
            System.out.println("Computer placed its " + currPlayer + " at "+ row + " " + col);

        } else if(currPlayer.equals(Content.NOUGHT)) {
            //Humans go

            System.out.println("");
            System.out.println("Player O please enter the location where you want to place your " + currPlayer + "\n"
                    + "The input should be (row[0-2] , column[0-2]) WITHOUT commas, and ONLY SPACES between two digits");

            row = in.nextInt();
            col = in.nextInt();

            System.out.println("");
        }

        if(row >= 0 && row < 3 && col >= 0 && col < 3 && grid.getContent(row, col).equals(Content.EMPTY)) {
            grid.setCell(row, col, currPlayer);
        }
        else {
            System.out.println("");
            System.out.println("This move at (" + (row + 1) + "," + (col + 1)
                    + ") is not valid. Try again...");
        }

    }

    public void initEngine(int first) {
        grid.clearGrid();
        currState = State.PLAYING;
        currPlayer = (first == 0) ? Content.NOUGHT : Content.CROSS;
    }

    public State getState() {
        return currState;
    }
}
