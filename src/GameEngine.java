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
        System.out.println("Press 0(zero) if you want to play first and 1(one) if you "+
                "\nwant computer to play first");
        try {
            firstPlayer = in.nextInt();
        }
        catch(Exception e) {
            System.out.println("invalid input");
        }
        while (firstPlayer < 0 || firstPlayer > 1) {
            System.out.println("Please enter either 1 or 0");
            while (!in.hasNextInt()) {
                System.out.println("That's not a number!");
                in.next();
            }
        }
        firstPlayer = in.nextInt();
        System.out.println("Game grid before start");
        grid.drawGrid();
        do {
            playerMove(currPlayer, computer);
            grid.drawGrid();
            updateGameState(currPlayer);

            if(currState == State.CROSS_WON) {
                System.out.println("Player X Won the game");
                System.out.println("=========================");
            }
            else if(currState == State.NOUGHT_WON) {
                System.out.println("Player O Won the game");
                System.out.println("=========================");
            }
            else if(currState == State.DRAW) {
                System.out.println("Game Drawn. Bye!");
                System.out.println("=========================");
            }

            if(currPlayer == Content.CROSS)
                currPlayer = Content.NOUGHT;
            else if(currPlayer == Content.NOUGHT)
                currPlayer = Content.CROSS;
        }
        // keep on playing until one player wins or the game is drawn
        while(currState == State.PLAYING);
    }

    private void updateGameState(Content currPlayer) {
        if (grid.hasWon(currPlayer)) {
            if (currPlayer == Content.CROSS)
                currState = State.CROSS_WON;
            if (currPlayer == Content.NOUGHT)
                currState = State.NOUGHT_WON;
        } else if (grid.isDraw()) {
            currState = State.DRAW;
        }
    }

//    private void playerMove(Content currPlayer, ComputerMinMax computer) {
//
//    }

    public void initEngine(int first) {
        grid.clearGrid();
        currState = State.PLAYING;
        currPlayer= (first == 0) ? Content.NOUGHT : Content.CROSS;
    }
}
