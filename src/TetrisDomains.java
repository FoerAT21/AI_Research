import java.util.ArrayList;

public class TetrisDomains {
    public ArrayList<ArrayList<OrderedPair>> domains;
    public TetrisDomains(Board board, final int numPieces){
        domains = new ArrayList<>();
        for(int piece = 0; piece < numPieces; piece++) {
            ArrayList<OrderedPair> domain = new ArrayList<>();
            for (int i = 0; i < board.getNumRows(); i++) {
                for (int j = 0; j < board.getNumColumns(); j++) {
                    domain.add(new OrderedPair(i, j));
                }
            }
            domains.add(domain);
        }
    }
}
