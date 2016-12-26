
public class Cell {

    private int row;
    private int col;
    private Content element;

    public Cell(int i, int j) {
        this.row = i;
        this.col = j;
        clear();
    }

    public void clear() {
        element = Content.EMPTY;
    }

    public Content getContent() {
        return element;
    }

    public void setContent(Content content) {
        element = content;
    }

    public void drawCell() {
        switch (element) {
            case CROSS: System.out.print("X");
                break;
            case NOUGHT: System.out.print("O");
                break;
            case EMPTY: System.out.print(" ");
                break;
        }
    }
}
