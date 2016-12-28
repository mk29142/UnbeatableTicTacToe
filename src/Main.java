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

    static void showInstructions()
    {
        System.out.println(" Instructions:"
                + "\n ======================"
                + "\n This is a Tic-Tac-Toe game. Your will be the first turn to play and you symbol is a"
                + "\n NOUGHT (o) and computer's symbol is a CROSS (X). You have to beat the computer."
                + "\n You have to enter the row and column number of the position you want to place"
                + "\n your symbol on the grid, with space between the two numbers. Please DON'T"
                + "\n separate the numbers with a comma."
                + "\n All the best. You will need luck to beat the computer player \n");
    }
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        State continueGame = null;

        int choice = 0;
        do{
            showMenu();
            try
            {
                choice = input.nextInt();
            }
            catch(Exception e)
            {
                System.out.println("invalid input");
            }
            while (choice <= 0 || choice > 4)
            {
                System.out.println("Please enter number between 1 to 4!");
                while (!input.hasNextInt())
                {
                    System.out.println("That's not a number!");
                    input.next(); // this is important!
                }
                choice = input.nextInt();
            }

            switch(choice)
            {

                case 1:
                    showInstructions();
                    break;

                case 2:
                    GameEngine newGame = new GameEngine();
                    continueGame = newGame.getState();
                    break;

                case 3:
                    break;

                default:
                    System.out.println("Please enter a valid input");
                    choice = 0;
            }
        }
        while(choice >= 0 && choice<4 || continueGame != null);

    }
}
