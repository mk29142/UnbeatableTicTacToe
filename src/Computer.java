
public abstract class Computer {

    protected int ROWS = 3;
    protected int COLS = 3;

    protected Cell[][] cells;
    protected Content computerSeed;    // computer's symbol
    protected Content playerSeed;   // Players's symbol

    public Computer(Grid grid) {
        this.cells = grid.getGrid();
    }

    public void setSeed(Content content) {
        this.computerSeed = content;
        this.playerSeed = (content.equals(Content.CROSS)) ? Content.NOUGHT : Content.CROSS;
    }


    abstract int[] move();

}
