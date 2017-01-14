package tictactoe.players;

import org.junit.After;
import org.junit.Test;
import tictactoe.GameEngine;
import tictactoe.data.Content;
import tictactoe.data.Grid;
import tictactoe.utils.InputNumberValidator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ComputerMinMaxTest {

    private Grid grid = new Grid();
    private Computer computer;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream in;
    private InputNumberValidator validator;
    private Scanner scanner;

    @After
    public void reset() {
        grid.clearGrid();
    }

    @Test
    public void moveTestForWin() throws Exception {
        grid.setCell(0, 0, Content.CROSS);
        grid.setCell(0,2, Content.CROSS);
        grid.setCell(1,1, Content.NOUGHT);
        grid.setCell(2, 0, Content.NOUGHT);
        grid.setCell(2,2,Content.NOUGHT);
        computer = new ComputerMinMax(grid);
        computer.setSeed(Content.CROSS);

        int[] res = computer.move();
        assertArrayEquals(new int[]{0,1}, res);
    }

    @Test
    public void movesSoThatPlayerDoesNotWin() throws Exception {
        grid.setCell(0, 1, Content.CROSS);
        grid.setCell(1,0, Content.CROSS);
        grid.setCell(2, 0, Content.NOUGHT);
        grid.setCell(2,2,Content.NOUGHT);
        computer = new ComputerMinMax(grid);
        computer.setSeed(Content.CROSS);

        int[] res = computer.move();
        assertArrayEquals(new int[]{2,1}, res);
    }

    @Test
    public void findsPositionToForceADraw() throws Exception {
        grid.setCell(0, 0, Content.NOUGHT);
        grid.setCell(1,0, Content.CROSS);
        grid.setCell(1, 1, Content.CROSS);
        grid.setCell(1,2,Content.NOUGHT);
        grid.setCell(2,0, Content.NOUGHT);
        computer = new ComputerMinMax(grid);
        computer.setSeed(Content.CROSS);

        int[] res = computer.move();
        assertArrayEquals(new int[]{0,2}, res);
    }

    @Test
    public void gameShouldBeADrawForComputerVsComputer() {
        in = new ByteArrayInputStream("3".getBytes());
        System.setIn(in);
        scanner = new Scanner(in);
        validator = new InputNumberValidator(scanner);
        System.setOut(new PrintStream(outContent));

        GameEngine gm = new GameEngine();
        gm.play();

        assertEquals(true, outContent.toString().contains("Game Drawn. Bye!\n=========================\n"));
    }


}