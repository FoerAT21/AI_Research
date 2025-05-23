/**
 * @author Stevie Michalik
 */
import java.util.*;

// Ultimately used the following article to help create the algorithm: http://aima.cs.berkeley.edu/newchap05.pdf
// Primarily used Section entitled "Intelligent backtracking: looking backward" to guide the creation of this algorithm
public class ConflictDirectedBackjumping {

    private Integer backTrack;

    public ConflictDirectedBackjumping(Integer initialBacktrack) {
        this.backTrack = initialBacktrack;
    }

    /**
     * An algorithm which performs Conflict Directed Back Jumping Search
     * @param domains The TetrisDomains value, indicating all possible domains for each piece
     * @param piecesAdded An ordered Set of Integers, indicating which pieces have been placed so far
     * @param conflictSet A Map of each Piece, and the Pieces whose placements created a conflict.
     * @param verbosity Level of detail shown about the results of the algorithm. Verbosity -1 does not print anything, Verbosity 0 just prints the final result, Verbosity 1 additionally prints when trying to assign a variable, and Verbosity 2 additionally prints the domain of each variable as well
     * @return A final TetrisDomains value if every variable has a domain, or null if there is not a solution
     */
    public TetrisDomains backtrack(final TetrisDomains domains, LinkedHashSet<Integer> piecesAdded, HashMap<Integer, LinkedHashSet<Integer>> conflictSet, int verbosity) {

        // If all pieces are placed, return the solution
        if (piecesAdded.size() == domains.domains.size()){
            if (verbosity >= 0) {
                System.out.printf("\nFinal Piece Domains: \n%s\n", domains);
            }
            return domains;
        }

        // If we have a variable to backtrack to, return null if this particular step has not reached the backtracking variable yet
        // Basically, we are going to ignore this particular stack call if we have a backtracking variable and still have not backtracked to it yet.
        if (this.backTrack != null && !Objects.equals(piecesAdded.getLast(), this.backTrack)) {
            return null;
        } else if (this.backTrack != null && Objects.equals(piecesAdded.getLast(), this.backTrack)) {
            // Reset the backtracking variable if we've backtracked to the right spot
            this.backTrack = null;
        }

        // Select a value to place a piece next for
        int mrv = minimumRemainingValues(domains, piecesAdded);
        ArrayList<ArrayList<OrderedPair>> domain = domains.domains.get(mrv);

        // Create a Deep Copy of the Conflict Set
        HashMap<Integer, LinkedHashSet<Integer>> newConflictSet = new HashMap<>();
        for (Map.Entry<Integer, LinkedHashSet<Integer>> entry : conflictSet.entrySet()) {
            newConflictSet.put(entry.getKey(), new LinkedHashSet<>(entry.getValue())); // Proper deep copy
        }

        for (ArrayList<OrderedPair> orderedPairs : domain) {
            // Print out certain details based upon verbosity
            if (verbosity >= 1) {
                System.out.printf("Piece Number to be Assigned: %d\n", mrv);
            }

            if (verbosity >= 2) {
                System.out.printf("Current Piece Domains: \n%s\n", domains);
            }

            // Make a deep copy of the Tetris Domains object
            TetrisDomains newDomains = new TetrisDomains(domains);

            // Getting the piece placement here
            ArrayList<ArrayList<OrderedPair>> temp = new ArrayList<>();
            temp.add(orderedPairs);

            // We place the piece here
            newDomains.domains.set(mrv, temp);
            piecesAdded.add(mrv);
            TetrisDomains result = null;

            // Forward Checking includes making updates to the Conflict Set, which are detailed in that method
            if (forwardCheck(orderedPairs, mrv, newDomains, newConflictSet)) {
                result = backtrack(newDomains, piecesAdded, newConflictSet, verbosity);
            }

            // If we successfully placed all pieces return the result
            if(result != null) return result;
            else piecesAdded.remove(mrv); // If it fails, we remove that piece that we placed
        }

        this.backTrack = null;

        // If every possible value for MRV fails, backjump to the most recent variable bjv in conf (MRV)
        LinkedHashSet<Integer> conflictVars = conflictSet.getOrDefault(mrv, new LinkedHashSet<>());
        if (conflictVars.isEmpty()) return null; // No valid backjump variable, terminate

        int backjumpVariable = new ArrayList<>(conflictVars).get(conflictVars.size() - 1);

        // Update the Conflict Set: Set conf (bjv) equal to conf (bjv) unioned with conf (MRV) − {bjv}.
        LinkedHashSet<Integer> backjumpVariableConflictSet = conflictSet.getOrDefault(backjumpVariable, new LinkedHashSet<>());

        backjumpVariableConflictSet.addAll(conflictVars);
        backjumpVariableConflictSet.remove(backjumpVariable);

        // Set Global backjump variable here
        this.backTrack = backjumpVariable;

        return null;
    }

    /**
     * Forward checks to see if there are any Pieces where we fully delete the domain of that pieces
     * @param value Coordinate Set for the place that we placed
     * @param skip The integer value of the Piece we placed
     * @param domains The current domain of each piece
     * @param conflictSet The current Conflict Set for the problem
     * @return A boolean indicating if any Piece's domain was fully removed or not
     */
    private static boolean forwardCheck(ArrayList<OrderedPair> value, int skip, TetrisDomains domains, HashMap<Integer, LinkedHashSet<Integer>> conflictSet) {
        boolean consistent = true;

        for (OrderedPair coordinate: value) {
            for(int i = 0; i < domains.domains.size(); i++){
                if(i == skip) continue;
                ArrayList<ArrayList<OrderedPair>> pieceDomain = domains.domains.get(i);

                Iterator<ArrayList<OrderedPair>> iter = pieceDomain.iterator();
                while (iter.hasNext()) {
                    ArrayList<OrderedPair> pos = iter.next();
                    for (OrderedPair checkPos : pos) {
                        if (checkPos.equals(coordinate)) {
                            // whenever forward checking based on an assignment to skip deletes a value from i’s domain, it should add skip to i’s conflict set
                            LinkedHashSet<Integer> conflictSetI = conflictSet.getOrDefault(i, new LinkedHashSet<>());
                            conflictSetI.add(skip);
                            conflictSet.put(i, conflictSetI);

                            iter.remove(); // Properly remove while iterating
                            break;
                        }
                    }
                }

                if (pieceDomain.isEmpty()) {
                    // Also, every time the last value is deleted from i’s domain, the variables in the conflict set of i are added to the conflict set of skip.
                    LinkedHashSet<Integer> conflictSetI = conflictSet.getOrDefault(i, new LinkedHashSet<>());
                    LinkedHashSet<Integer> conflictSetSkip = conflictSet.getOrDefault(skip, new LinkedHashSet<>());
                    conflictSetSkip.addAll(conflictSetI);
                    conflictSet.put(skip, conflictSetSkip);

                    consistent = false;
                }
            }
        }
        return consistent;
    }

    /**
     * Finds the Minimum Remaining Value in the Tetris Domains.
     * @param domains Domain of each piece to be added
     * @param piecesAdded List of all pieces already added
     * @return An integer of which piece to add next
     */
    public static int minimumRemainingValues(TetrisDomains domains, LinkedHashSet<Integer> piecesAdded) {
        int min_index = 0;
        while (min_index < domains.domains.size() && piecesAdded.contains(min_index)) {
            min_index++;
        }

        if(min_index >= domains.domains.size()) return min_index;

        for (int i = min_index+1; i < domains.domains.size(); i++) {
            ArrayList<ArrayList<OrderedPair>> min_domain = domains.domains.get(min_index);
            ArrayList<ArrayList<OrderedPair>> curr_domain = domains.domains.get(i);

            if(min_domain.size() > curr_domain.size() && !piecesAdded.contains(i))
                min_index = i;
        }

        return min_index;
    }

}
