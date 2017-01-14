package tictactoe.utils;

import tictactoe.data.Content;
import tictactoe.data.State;

public class OutputOptions {

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
                "\n Human vs Computer and Computer vs Computer." +
                "\n The cells are numbers from left to right start at 1, as shown below." +
                "\n 1 | 2 | 3 " +
                "\n ---------" +
                "\n 4 | 5 | 6 " +
                "\n ---------" +
                "\n 7 | 8 | 9 " +
                "\n When prompted pick a number between 1 and 9 inclusive.\n");
    }

    public void showGameModes() {
        System.out.println("Please choose game mode");
        System.out.println("Press 1 for Human Vs Computer " +
                "\nPress 2 for Human Vs Human" +
                "\nPress 3 for Computer Vs Computer");
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

    public void printUserInputRequest(Content currPlayer) {
        System.out.println("");
        System.out.println("Player O please enter the location where you want to place your " + currPlayer + "\n"
                + "The input should be between 1 and 9 inclusive.");
    }

    public void printHumanVsHumanLegend() {
        System.out.println("\nThe symbol for player 2 is X and your symbol is O\n");
    }

    public void printHumanVsComputerLegend() {
        System.out.println("\nThe symbol for computer is X and your symbol is O\n");
    }

    public void askWhoStarts() {
        System.out.println("Press 0(zero) if you want to play first and 1(one) if you " +
                "\nwant computer to play first");
    }

    public void invalidMove(int row, int col) {
        System.out.println("");
        System.out.println("This move at (" + row + "," + col
                + ") is not valid. Try again...");
    }

    public void printMadeMove(Content currPlayer, int row, int col) {
        System.out.println("");
        System.out.println("Computer placed its " + currPlayer + " at "+ row + " " + col);
    }
}
