
public class Options {

    public void showMenu() {
        System.out.println("MENU:");
        System.out.println("--------------------");
        System.out.println("1) Intructions");
        System.out.println("2) Play Game");
        System.out.println("3) Exit");
        System.out.println("--------------------");
    }

    public void showInstructions() {
        System.out.println(" Instructions:"
                + "\n ======================"
                + "\n This is a Tic-Tac-Toe game. There are 3 game modes, Human vs Human, " +
                "\n Human vs Computer and Computer vs Computer." +
                "\n When prompted for an input please enter the row and column number in that " +
                "\n order WITHOUT commas and ONLY a space.\n");
    }

}
