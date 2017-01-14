package tictactoe.utils;

import java.util.Scanner;

public class InputValidator {

    private final Scanner in;

    public InputValidator(Scanner in) {
        this.in = in;
    }

    public void clearStreamOfNonIntegers() {
        while (!in.hasNextInt()) {
            System.out.println("That's not a number!");
            in.next();
        }
    }

    public int getNumberInRange(int val, int lowerBound, int upperBound) {
        int value = val;
        while (value < lowerBound || value > upperBound) {
            System.out.println("Please enter number between " + lowerBound + " to " + upperBound + "!");
            clearStreamOfNonIntegers();
            value = in.nextInt();
        }
        return value;
    }
}
