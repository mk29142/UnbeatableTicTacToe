package tictactoe.utils;

import tictactoe.utils.OutputOptions;
import org.junit.Before;
import org.junit.Test;
import tictactoe.data.Content;
import tictactoe.data.State;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class OutputOptionsTest {

    private OutputOptions outputOptions;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @Before
    public void setUp() throws Exception {
        outputOptions = new OutputOptions();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void showMenu() throws Exception {
        outputOptions.showMenu();
        assertEquals(true, outContent.toString().contains("2) Play Game"));
    }

    @Test
    public void showInstructions() throws Exception {
        outputOptions.showInstructions();
        assertEquals(true, outContent.toString().contains("When prompted pick a number between 1 and 9 inclusive."));
    }

    @Test
    public void showGameModes() throws Exception {
        outputOptions.showGameModes();
        assertEquals(true, outContent.toString().contains("Please choose game mode"));
    }

    @Test
    public void printWinningPlayerCROSS() throws Exception {
        outputOptions.printWinningPlayer(State.CROSS_WON);
        assertEquals("Player X Won the game\n=========================\n", outContent.toString());
    }

    @Test
    public void printWinningPlayerNOUGHT() throws Exception {
        outputOptions.printWinningPlayer(State.NOUGHT_WON);
        assertEquals("Player O Won the game\n=========================\n", outContent.toString());
    }

    @Test
    public void printWinningPlayerDRAW() throws Exception {
        outputOptions.printWinningPlayer(State.DRAW);
        assertEquals("Game Drawn. Bye!\n=========================\n", outContent.toString());
    }

    @Test
    public void printUserInputRequest() throws Exception {
        outputOptions.printUserInputRequest(Content.CROSS);
        assertEquals(true, outContent.toString().contains("Please enter the location where you want to place your"));
    }

    @Test
    public void printHumanVsHumanLegend() throws Exception {
        outputOptions.printHumanVsHumanLegend();
        assertEquals("\nThe symbol for player 2 is X and your symbol is O\n\n", outContent.toString());
    }

    @Test
    public void printHumanVsComputerLegend() throws Exception {
        outputOptions.printHumanVsComputerLegend();
        assertEquals("\nThe symbol for computer is X and your symbol is O\n\n", outContent.toString());
    }

    @Test
    public void askWhoStarts() throws Exception {
        outputOptions.askWhoStarts();
        assertEquals("Press 0(zero) if you want to play first and 1(one) if you " +
                "\nwant computer to play first\n", outContent.toString());
    }

    @Test
    public void invalidMove() throws Exception {
        outputOptions.invalidMove(1,1);
        assertEquals("\nThis move at (1,1) is not valid. Try again...\n", outContent.toString());
    }

    @Test
    public void printMadeMove() throws Exception {
        outputOptions.printMadeMove(Content.CROSS, 1, 1);
        assertEquals("\nComputer placed its " + Content.CROSS + " at "+ 1 + " " + 1+ "\n", outContent.toString());
    }

}