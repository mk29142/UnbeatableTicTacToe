package tictactoe.utils;

import tictactoe.data.State;

public class Options {

    public void showMenu() {
        System.out.println("MENU:");
        System.out.println("--------------------");
        System.out.println("1) Intructions");
        System.out.println("2) Play Game");
        System.out.println("3) Exit");
        System.out.println("--------------------");
    }

    public void showInstructions() {
        System.out.println(" Instructions:"
                + "\n ======================"
                + "\n This is a Tic-Tac-Toe game. There are 3 game modes, Human vs Human, " +
                "\n Human vs tictactoe.players.Computer and tictactoe.players.Computer vs tictactoe.players.Computer." +
                "\n When prompted for an input please enter the row and column number in that " +
                "\n order WITHOUT commas and ONLY a space.\n");
    }

    public void showGameModes() {
        System.out.println("Please choose gameMode");
        System.out.println("Press 1 for Human Vs tictactoe.players.Computer " +
                "\nPress 2 for Human Vs Human" +
                "\nPress 3 for tictactoe.players.Computer Vs tictactoe.players.Computer");
    }

    public void printWinningPlayer(State currState) {
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
    }
}
