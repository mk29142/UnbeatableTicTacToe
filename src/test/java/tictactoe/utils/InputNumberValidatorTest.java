package tictactoe.utils;

import tictactoe.utils.InputNumberValidator;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class InputNumberValidatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayInputStream in;
    private InputNumberValidator validator;
    private Scanner scanner;


    @Before
    public void init() {
        in = new ByteArrayInputStream("a b c d 4 3 6".getBytes());
        System.setIn(in);
        scanner = new Scanner(in);
        validator = new InputNumberValidator(scanner);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void clearStreamOfNonIntegers() throws Exception {
        validator.clearStreamOfNonIntegers();
        assertEquals(scanner.next(), "4");
    }

    @Test
    public void printsCorrectlyWhenClearingNonIntegers() {
        validator.clearStreamOfNonIntegers();
        String message = "";
        for(int i = 0; i < 4; i++) {
            message += "That's not a number!\n";
        }
        assertEquals(message, outContent.toString());
    }

    @Test
    public void getNumberInRange() throws Exception {
        validator.clearStreamOfNonIntegers();

        int res = validator.getNumberInRange(scanner.nextInt(), 5, 7);
        assertEquals(6, res);

    }

    @Test
    public void getNumberInRangePrintsCorrectMessage() throws Exception {
        in = new ByteArrayInputStream("4 3 6".getBytes());
        System.setIn(in);
        scanner = new Scanner(in);
        validator = new InputNumberValidator(scanner);

        String message = "";
        for(int i = 0; i < 2; i++) {
            message += "Please enter number between 5 to 7!\n";
        }

        int res = validator.getNumberInRange(scanner.nextInt(), 5, 7);
        assertEquals(message, outContent.toString());

    }

}