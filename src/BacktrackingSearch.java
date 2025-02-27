import java.util.ArrayList;

public class BacktrackingSearch {
    private TetrisDomains domains;
    private ArrayList<Piece> pieces;
    private Board board;

    public BacktrackingSearch(final int numRows, final int numCols,
                              final ArrayList<Piece> pieces){
        board = new Board(numRows, numCols);
        domains = new TetrisDomains(board, pieces);
        this.pieces = pieces;
    }

    public TetrisDomains backtrack(final int nextVarToAssign){
        if(domains.domainsAllSet()) return domains;

        return null;
    }

    private int minimumRemainingValues(){
        int min_index = 0;
        for(int i = 1; i < domains.domains.size(); i++){
            ArrayList<ArrayList<OrderedPair>> min_domain = domains.domains.get(min_index);
            ArrayList<ArrayList<OrderedPair>> curr_domain = domains.domains.get(i);

            if(min_domain.size() > curr_domain.size()) min_index = i;
        }

        return min_index;
    }
}
