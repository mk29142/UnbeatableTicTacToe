/**
 * Created by mridulkumar on 26/12/2016.
 */
public class Cell {

    private int row;
    private int col;
    Content element;

    public Cell(int i, int j) {
        this.row = i;
        this.col = j;
        clear();
    }

    private void clear() {
        element = Content.EMPTY;
    }

    public Content getContent() {
        return element;
    }
}
