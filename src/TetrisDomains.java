import java.util.ArrayList;

public class TetrisDomains {
    /***
     * Outer array list is the list for all domains of all pieces
     * Middle array list is the list of all possible positions for piece x
     * Inner array list is going to be a length of 4 (location on the board where
     * piece can go
     */

    public ArrayList<ArrayList<ArrayList<OrderedPair>>> domains;
    public TetrisDomains(Board board, ArrayList<Piece> pieces){
        domains = new ArrayList<>();
        for(Piece piece : pieces){
            domains.add(generateDomain(board.getNumRows(), board.getNumColumns(), piece));
        }
    }

    private ArrayList<ArrayList<OrderedPair>> generateDomain(int numRows,
                                                             int numCols, Piece piece){
        ArrayList<ArrayList<OrderedPair>> returnable = new ArrayList<>();
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < numCols; col++){
                ArrayList<OrderedPair> positions = new ArrayList<>();
                boolean add = true;
                for(OrderedPair op : piece.getPositions()){
                    int r = op.getX()+row;
                    int c = op.getY()+col;
                    if(r >= numRows || c >= numRows) {
                        add = false;
                        break;
                    }
                    positions.add(new OrderedPair(r,c));
                }
                if(add) returnable.add(positions);
            }
        }
        return returnable;
    }

    public boolean domainsAllSet(){
        for(ArrayList<ArrayList<OrderedPair>> domain : this.domains)
            if(domain.size() != 1) return false;

        return true;
    }
}
