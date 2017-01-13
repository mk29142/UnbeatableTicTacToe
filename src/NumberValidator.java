import java.util.Scanner;

public class NumberValidator {

    private final Scanner in;

    public NumberValidator(Scanner in) {
        this.in = in;
    }

    public void clearStreamOfNonNumbers() {
        while (!in.hasNextInt()) {
            System.out.println("That's not a number!");
            in.next();
        }
    }

    public int getNumberInRange(int val, int lowerBound, int upperBound) {
        int value = val;
        while (value < lowerBound || value > upperBound) {
            System.out.println("Please enter number between " + lowerBound + " to " + upperBound + "!");
            clearStreamOfNonNumbers();
            value = in.nextInt();
        }
        return value;
    }
}
