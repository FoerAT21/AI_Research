/**
 * @author Andrew Foerst
 */
import java.util.ArrayList;

public class TetrisDomains {
    /***
     * Outer array list is the list for all domains of all pieces
     * Middle array list is the domain for piece x
     * Inner array list is going to be a length of 4 (location on the board where
     * piece can go
     */

    public ArrayList<ArrayList<ArrayList<OrderedPair>>> domains;

    public TetrisDomains(Board board, ArrayList<Piece> pieces){
        domains = new ArrayList<>();
        for(Piece piece : pieces){
            domains.add(
                generateDomain(board.getNumRows(), board.getNumColumns(), piece)
            );
        }
    }

    public TetrisDomains(final TetrisDomains oldDomains){
        this.domains = new ArrayList<>();

        for (ArrayList<ArrayList<OrderedPair>> domain : oldDomains.domains) {
            ArrayList<ArrayList<OrderedPair>> domainCopy = new ArrayList<>();

            for (ArrayList<OrderedPair> list : domain) {
                ArrayList<OrderedPair> listCopy = new ArrayList<>();

                for (OrderedPair pair : list) {
                    listCopy.add(new OrderedPair(pair.getX(), pair.getY())); // Assuming OrderedPair has x and y fields
                }

                domainCopy.add(listCopy);
            }

            this.domains.add(domainCopy);
        }

    }

    /**
     * This is effectively AC-3. Creates domains of the pieces that are consistent
     * with each other and the problem itself
     * @param numRows - number of rows to generate rows
     * @param numCols - number of columns to generate domains
     * @param piece - the piece that we are generating domains for
     * @return a list of lists of ordered pairs for each piece
     */
    private ArrayList<ArrayList<OrderedPair>> generateDomain(int numRows,
                                                             int numCols, Piece piece) {
        ArrayList<ArrayList<OrderedPair>> returnable = new ArrayList<>();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                ArrayList<OrderedPair> positions = new ArrayList<>();
                boolean add = true;
                for (OrderedPair op : piece.getPositions()) {
                    int c = op.getX() + col;
                    int r = op.getY() + row;

                    if (r >= numRows || c >= numCols || r < 0 || c < 0) {
                        add = false;
                        break;
                    }
                    positions.add(new OrderedPair(r, c));
                }
                if (add) {
                    returnable.add(positions);
                }
            }
        }
        return returnable;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < domains.size(); i++){
            ArrayList<ArrayList<OrderedPair>> domain = domains.get(i);
            sb.append("Piece ").append(i + 1).append(" domain: ").append(domain).append("\n");
        }

        return sb.toString();
    }
}
