package tictactoe;

import tictactoe.GameEngine;
import tictactoe.utils.InputNumberValidator;
import tictactoe.utils.OutputOptions;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        InputNumberValidator inputNumberValidator = new InputNumberValidator(input);
        GameEngine newGame = new GameEngine();
        OutputOptions outputOptions = new OutputOptions();

        int choice = 0;

        while (true) {
            outputOptions.showMenu();
            inputNumberValidator.clearStreamOfNonIntegers();

            choice = input.nextInt();

            choice = inputNumberValidator.getNumberInRange(choice, 0, 4);

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
