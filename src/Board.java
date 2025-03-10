/**
 * @author Stevie Michalik
 */
import java.util.ArrayList;
import java.util.Collections;

public class Board {

    private final ArrayList<ArrayList<Boolean>> board;
    private final int numRows;
    private final int numColumns;

    /**
     * Constructor which sets up an empty board
     * @param numRows Number of rows for the new Board. Each row is a List in the board variable.
     * @param numColumns Number of rows for the new Board. Each column is a value in each row in the board variable
     */
    public Board(int numRows, int numColumns) {
        ArrayList<ArrayList<Boolean>> board = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            ArrayList<Boolean> innerList = new ArrayList<>(Collections.nCopies(numColumns, false));
            board.add(innerList);
        }

        this.board = board;
        this.numRows = numRows;
        this.numColumns = numColumns;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public int getNumRows(){
        return numRows;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numRows; i++){
            sb.append(String.format("Row %d: ", i));
            for (int j = 0; j < numColumns; j++){
                sb.append(String.format("%s, ", this.board.get(i).get(j)));
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
