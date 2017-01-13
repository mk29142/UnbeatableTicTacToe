import java.util.Scanner;

public class Main {

    static void showMenu() {
            System.out.println("MENU:");
            System.out.println("--------------------");
            System.out.println("1) Intructions");
            System.out.println("2) Play Game");
            System.out.println("3) Exit");
            System.out.println("--------------------");
    }

    static void showInstructions() {
        System.out.println(" Instructions:"
                + "\n ======================"
                + "\n This is a Tic-Tac-Toe game. There are 3 game modes, Human vs Human, " +
                "\n Human vs Computer and Computer vs Computer." +
                "\n When prompted for an input please enter the row and column number in that " +
                "\n order WITHOUT commas and ONLY a space.\n");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int choice = 0;

        do {
            showMenu();
            try {
                choice = input.nextInt();
            }
            catch(Exception e) {
                System.out.println("invalid input");
            }
            while (choice <= 0 || choice > 4) {
                System.out.println("Please enter number between 1 to 4!");
                while (!input.hasNextInt()) {
                    System.out.println("That's not a number!");
                    input.next();
                }
                choice = input.nextInt();
            }

            switch(choice) {

                case 1:
                    showInstructions();
                    break;

                case 2:
                    GameEngine newGame = new GameEngine();
                    newGame.play();
                    break;

                case 3:
                    return;

                default:
                    System.out.println("Please enter a valid input");
                    choice = 0;
            }
        } while(choice >= 0 && choice < 4);
    }
}
