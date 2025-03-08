import java.util.*;

// http://aima.cs.berkeley.edu/newchap05.pdf
// https://cse.unl.edu/~choueiry/Documents/Hybrid-Prosser.pdf
// https://cs.stackexchange.com/questions/119343/need-recursive-version-of-conflict-based-backjumping
public class ConflictDirectedBackjumping {

    private Integer backTrack;

    public ConflictDirectedBackjumping(Integer initialBacktrack) {
        this.backTrack = initialBacktrack;
    }

    /**
     *
     * @param domains
     * @param piecesAdded
     * @param conflictSet
     * @param verbosity Level of detail shown about the results of the algorithm. Verbosity 0 just prints the final result, Verbosity 1 additionally prints when trying to assign a variable, and Verbosity 2 additionally prints the domain of each variable as well
     * @return
     */
    public TetrisDomains backtrack(final TetrisDomains domains, LinkedHashSet<Integer> piecesAdded, HashMap<Integer, LinkedHashSet<Integer>> conflictSet, int verbosity) {

        // If all pieces are placed, return the solution
        if (piecesAdded.size() == domains.domains.size()){
            System.out.printf("\nFinal Piece Domains: \n%s\n", domains);
            return domains;
        }

        // If we have a variable to backtrack to, return null if this particular step has not reached the backtracking variable yet
        // Basically, we are going to ignore this particular stack call if we have a backtracking variable and still have not backtracked to it yet.
        if (this.backTrack != null && !Objects.equals(piecesAdded.getLast(), this.backTrack)) {
            return null;
        }

        int mrv = minimumRemainingValues(domains, piecesAdded);
        ArrayList<ArrayList<OrderedPair>> domain = domains.domains.get(mrv);

        for (ArrayList<OrderedPair> orderedPairs : domain) {
            // Print out certain details based upon verbosity
            if (verbosity >= 1) {
                System.out.printf("Piece Number to be Assigned: %d\n", mrv);
            }

            if (verbosity >= 2) {
                System.out.printf("Current Piece Domains: \n%s\n", domains);
            }

            TetrisDomains newDomains = new TetrisDomains(domains);
            HashMap<Integer, LinkedHashSet<Integer>> newConflictSet = new HashMap<>(conflictSet);

            // Getting the piece placement here
            ArrayList<ArrayList<OrderedPair>> temp = new ArrayList<>();
            temp.add(orderedPairs);

            // We place the piece here
            newDomains.domains.set(mrv, temp);
            piecesAdded.add(mrv);
            TetrisDomains result = null;

            if (forwardCheck(orderedPairs, mrv, newDomains, newConflictSet, piecesAdded)) {
                result = backtrack(newDomains, piecesAdded, newConflictSet, verbosity);
            }

            // If we successfully placed all pieces return the result
            if(result != null) {
                this.backTrack = null;
                return result;
            }
            else piecesAdded.remove(mrv); // If it fails, we remove that piece that we placed
        }

        this.backTrack = null;
        // If every possible value for Xj fails, backjump to the most recent variable Xi in conf (Xj ), and set conf (Xi) ← conf (Xi) ∪ conf (Xj ) − {Xi}.
        System.out.println(piecesAdded);
        System.out.println(conflictSet);
        LinkedHashSet<Integer> conflictVars = conflictSet.getOrDefault(mrv, new LinkedHashSet<>());
        if (conflictVars.isEmpty()) return null; // No valid backjump variable, terminate

        int backjumpVariable = new ArrayList<>(conflictVars).get(conflictVars.size() - 1);

        LinkedHashSet<Integer> backjumpVariableConflictSet = conflictSet.getOrDefault(backjumpVariable, new LinkedHashSet<>());
        LinkedHashSet<Integer> assignmentConflictSet = conflictSet.getOrDefault(mrv, new LinkedHashSet<>());

        assignmentConflictSet.remove(backjumpVariable);

        for (Integer toAdd : assignmentConflictSet) {
            if (piecesAdded.contains(toAdd)) {
                backjumpVariableConflictSet.add(toAdd);
            }
        }

        conflictSet.put(mrv, new LinkedHashSet<>());

        System.out.printf("Assigned: %d; Back-jump: %d\n", mrv, backjumpVariable);
        System.out.println(conflictSet);
        System.out.println();

        this.backTrack = backjumpVariable;

        return null;
    }

    private static boolean forwardCheck(ArrayList<OrderedPair> value, int skip, TetrisDomains domains, HashMap<Integer, LinkedHashSet<Integer>> conflictSet, LinkedHashSet<Integer> piecesAdded) {
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
                            // only add to conflict set if it's one of the pieces added so far
                            if (piecesAdded.contains(skip) && piecesAdded.getLast() != skip) {
                                LinkedHashSet<Integer> currentSet = conflictSet.getOrDefault(i, new LinkedHashSet<>());
                                currentSet.add(skip);
                                conflictSet.put(i, currentSet);
                            }
                            iter.remove(); // Properly remove while iterating
                            break;
                        }
                    }
                }

                if (pieceDomain.isEmpty()) {
                    // Also, every time the last value is deleted from Y ’s domain, the variables in the conflict set of Y are added to the conflict set of X.
                    LinkedHashSet<Integer> checkSet = conflictSet.getOrDefault(i, new LinkedHashSet<>());
                    LinkedHashSet<Integer> assignmentSet = conflictSet.getOrDefault(skip, new LinkedHashSet<>());

                    for (Integer toAdd : checkSet){
                        if (piecesAdded.contains(toAdd) && !Objects.equals(piecesAdded.getLast(), toAdd)){
                            assignmentSet.add(toAdd);
                        }
                    }
                    assignmentSet.remove(skip);
                    conflictSet.put(skip, assignmentSet);
                    consistent = false;
                }
            }
        }
        return consistent;
    }

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
