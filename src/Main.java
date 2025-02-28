import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.HashMap;

public class Main {
    final static int NUM_ROWS = 10;
    final static int NUM_COLS = 10;
    final static int NUM_PIECES = (NUM_ROWS*NUM_COLS)/4;

    public static void main(String[] args) {
        ArrayList<Piece> pieces= new ArrayList<>();
        HashMap<String, Integer> pieceCount = new HashMap<>();

        Random rand = new Random();

        for(int i = 0; i < NUM_PIECES; i++){
            Piece piece = generatePiece(rand.nextInt(7));
            int count = pieceCount.getOrDefault(piece.toString(), 0) + 1;
            pieceCount.put(piece.toString(), count);
            pieces.add(piece);
        }


        System.out.println("Trying a board of size " + NUM_ROWS + "x" +NUM_COLS + " with:");
        pieceCount.forEach((key, value) -> {
            if(value == 1) System.out.println(value + " " + key);
            else System.out.println(value + " " + key + "s");
        });


        Board board = new Board(NUM_ROWS,NUM_COLS);
        TetrisDomains domains = new TetrisDomains(board, pieces);
        TetrisDomains solution = BacktrackingSearch.backtrack(domains, new HashSet<>());

        if(solution == null){
            System.out.println("Didn't work");
        }else{
            System.out.println(solution.domains);
        }
    }
    private static Piece generatePiece(int piece){
        return switch (piece) {
            case 1 -> new I();
            case 2 -> new S();
            case 3 -> new Z();
            case 4 -> new L();
            case 5 -> new J();
            case 6 -> new T();
            default -> new O();
        };
    }

}