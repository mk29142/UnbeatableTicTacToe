package tictactoe;

import tictactoe.utils.NumberValidator;
import tictactoe.utils.Options;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        NumberValidator numberValidator = new NumberValidator(input);
        GameEngine newGame = new GameEngine();
        Options options = new Options();

        int choice = 0;

        while (true) {
            options.showMenu();
            numberValidator.clearStreamOfNonNumbers();

            choice = input.nextInt();

            choice = numberValidator.getNumberInRange(choice, 0, 4);

            switch(choice) {

                case 1:
                    options.showInstructions();
                    break;

                case 2:
                    newGame.play();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Please enter a valid input");
                    choice = 0;
            }
        }
    }
}
