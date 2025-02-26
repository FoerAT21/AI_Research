import java.util.ArrayList;

public class BacktrackingSearch {
    private TetrisDomains domains;
    private ArrayList<Piece> pieces;
    private Board board;

    public BacktrackingSearch(final int numRows, final int numCols, final int numPieces){
        board = new Board(numRows, numCols);
        domains = new TetrisDomains(board, numPieces);

    }
}
