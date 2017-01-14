package tictactoe;

import tictactoe.utils.InputValidator;
import tictactoe.utils.OutputOptions;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator(input);
        GameEngine newGame = new GameEngine();
        OutputOptions outputOptions = new OutputOptions();

        int choice = 0;

        while (true) {
            outputOptions.showMenu();
            inputValidator.clearStreamOfNonIntegers();

            choice = input.nextInt();

            choice = inputValidator.getNumberInRange(choice, 0, 4);

            switch(choice) {

                case 1:
                    outputOptions.showInstructions();
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
