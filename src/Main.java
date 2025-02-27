import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ArrayList<Piece> pieces= new ArrayList<>();
        final int NUM_PIECES = 1;
        for(int i = 0; i<NUM_PIECES; i++){
            pieces.add(new J());
        }

        Board board = new Board(3,3);
        TetrisDomains domains = new TetrisDomains(board, pieces);
        System.out.println(domains.domains);
//        TetrisDomains solution = BacktrackingSearch.backtrack(domains);
//        System.out.println(solution);
//        if(solution == null){
//            System.out.println("Didn't work");
//        }else{
//            System.out.println("here");
//            System.out.println(solution.domains);
//        }
    }

}