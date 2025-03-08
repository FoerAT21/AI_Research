import java.util.*;

public class Main {
    final static int NUM_ROWS = 7;
    final static int NUM_COLS = 5;
    final static int NUM_EXPERIMENT_ITERATIONS = 5;
    final static int VERBOSITY = 0;
    final static int NUM_PIECES = (NUM_ROWS*NUM_COLS)/4;

    public static void main(String[] args) {

        // Run the experiments with Backtracking Search
        System.out.println("BACKTRACKING SEARCH");
        int numSolutionsFoundBacktracking = 0;
        long[] solutionTimeBacktracking = new long[NUM_EXPERIMENT_ITERATIONS];

        for (int i = 0; i < NUM_EXPERIMENT_ITERATIONS; i++) {
            ArrayList<Piece> pieces = generatePieces();
            Board board = new Board(NUM_ROWS,NUM_COLS);
            TetrisDomains domains = new TetrisDomains(board, pieces);

            long startTime = System.nanoTime();
            TetrisDomains solution = BacktrackingSearch.backtrack(domains, new HashSet<>(), VERBOSITY);
            long estimatedTime = System.nanoTime() - startTime;
            solutionTimeBacktracking[i] = estimatedTime;

            if (solution != null) {
                numSolutionsFoundBacktracking++;
            } else {
                System.out.printf("No solution found for iteration %d\n", i);
            }
        }

        printEvaluationMetrics(numSolutionsFoundBacktracking, solutionTimeBacktracking);

        // The same, just with Conflict Directed Backjumping
        System.out.println("\nCONFLICT DIRECTED BACKJUMPING");
        int numSolutionsFoundConflictDirectedBackjumping = 0;
        long[] solutionTimeConflictDirectedBackjumping = new long[NUM_EXPERIMENT_ITERATIONS];

        for (int i = 0; i < NUM_EXPERIMENT_ITERATIONS; i++) {
            ArrayList<Piece> pieces = generatePieces();
            Board board = new Board(NUM_ROWS,NUM_COLS);
            TetrisDomains domains = new TetrisDomains(board, pieces);
            ConflictDirectedBackjumping cbj = new ConflictDirectedBackjumping(null);

            long startTime = System.nanoTime();
            TetrisDomains solution = cbj.backtrack(domains, new LinkedHashSet<>(), new HashMap<>(), VERBOSITY);
            long estimatedTime = System.nanoTime() - startTime;
            solutionTimeConflictDirectedBackjumping[i] = estimatedTime;

            if (solution != null) {
                numSolutionsFoundConflictDirectedBackjumping++;
            } else {
                System.out.printf("No solution found for iteration %d\n", i);
            }
        }

        printEvaluationMetrics(numSolutionsFoundConflictDirectedBackjumping, solutionTimeConflictDirectedBackjumping);
    }

    private static void printEvaluationMetrics (float solutionsFound, long[] backtrackingTimes) {
        float percentOfSolutions = (solutionsFound/NUM_EXPERIMENT_ITERATIONS)*100;
        System.out.printf("Percentage of solutions found: %.2f%%\n", percentOfSolutions);

        long totalTime = 0;
        for (int i = 0; i < backtrackingTimes.length; i++) {
            totalTime += backtrackingTimes[i];
        }

        float avgTime = (float) (totalTime/NUM_EXPERIMENT_ITERATIONS)/1000000;
        System.out.printf("Average Calculation Time: %.2f ms\n", avgTime);
    }

    private static ArrayList<Piece> generatePieces() {
        ArrayList<Piece> pieces = new ArrayList<>();
        Random rand = new Random();

        for(int i = 0; i < NUM_PIECES; i++){
            Piece piece = generatePiece(rand.nextInt(7));
            pieces.add(piece);
        }

        return pieces;
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