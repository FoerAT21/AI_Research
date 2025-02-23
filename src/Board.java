import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private List<List<Boolean>> board;
    private final int numRows;
    private final int numColumns;

    /**
     * Constructor which sets up an empty board
     * @param numRows Number of rows for the new Board. Each row is a List in the board variable.
     * @param numColumns Number of rows for the new Board. Each column is a value in each row in the board variable
     */
    public Board(int numRows, int numColumns) {
        List<List<Boolean>> board = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Boolean> innerList = Collections.nCopies(numColumns, false);
            board.add(innerList);
        }

        this.board = board;
        this.numRows = numRows;
        this.numColumns = numColumns;
    }

    public void placePiece() throws NotImplementedException {
        // @TODO: implement method
        throw new NotImplementedException("Not yet implemented");
    }

    private void updateSpot(int i, int j, boolean value) {
        this.board.get(i).set(j, value);
    }

    private int numEmptySpaces() {
        int totalCount = 0;
        for (int i = 0; i < numRows; i++){
            for (int j = 0; j < numColumns; j++){
                if (!this.board.get(i).get(j)){
                    totalCount++;
                }
            }
        }
        return totalCount;
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
