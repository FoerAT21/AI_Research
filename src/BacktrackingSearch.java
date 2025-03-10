/**
 * @author Andrew Foerst
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class BacktrackingSearch {

    /**
     * An algorithm which performs basic backtracking search with forward checking
     * @param domains The TetrisDomains value, indicating all possible domains for each piece
     * @param piecesAdded An ordered Set of Integers, indicating which pieces have been placed so far
     * @param verbosity Level of detail shown about the results of the algorithm. Verbosity -1 does not print anything, Verbosity 0 just prints the final result, Verbosity 1 additionally prints when trying to assign a variable, and Verbosity 2 additionally prints the domain of each variable as well
     * @return A final TetrisDomains value if every variable has a domain, or null if there is not a solution
     */
    public static TetrisDomains backtrack(final TetrisDomains domains, HashSet<Integer> piecesAdded, int verbosity){

        // If all domains are set we return out
        if (piecesAdded.size() == domains.domains.size()){
            if (verbosity >= 0) {
                System.out.printf("\nFinal Piece Domains: \n%s\n", domains);
            }
            return domains;
        }

        // Get the index of the piece that has the smallest domain
        // so we can place it
        int mrv = minimumRemainingValues(domains, piecesAdded);
        // Get the domain of the piece we just placed
        ArrayList<ArrayList<OrderedPair>> domain = domains.domains.get(mrv);

        // Go through every value in the domain of the current piece
        // and try to place it
        for (ArrayList<OrderedPair> orderedPairs : domain) {
            // Print out certain details based upon verbosity
            if (verbosity >= 1) {
                System.out.printf("Piece Number to be Assigned: %d\n", mrv);
            }

            if (verbosity >= 2) {
                System.out.printf("Current Piece Domains: \n%s\n", domains);
            }

            // Create a new domains that holds the old values but changes the value
            // of the piece to place to where we are placing it
            TetrisDomains newDomains = new TetrisDomains(domains);

            // Getting the piece placement here
            ArrayList<ArrayList<OrderedPair>> temp = new ArrayList<>();
            temp.add(orderedPairs);

            // We place the piece here
            newDomains.domains.set(mrv, temp);
            piecesAdded.add(mrv);

            // Create a result that will be changed after forward checking
            TetrisDomains result = null;

            // Forward check returns true if there are no domains that are empty in
            // new domains
            if (forwardCheck(orderedPairs, mrv, newDomains)) {
                result = backtrack(newDomains, piecesAdded, verbosity);
            }

            // If we successfully placed all pieces return the result
            if(result != null) return result;
            else  piecesAdded.remove(mrv); // If it fails, we remove that piece that we placed
        }

        return null;
    }

    /**
     * Does forward checking
     * @param value - the piece that we just placed
     * @param skip - the index of the domains that we should skip removing from
     * @param domains - the domains that we are removing from
     * @return false if there is a zero domain, true if there is not
     */
    private static boolean forwardCheck(ArrayList<OrderedPair> value, int skip,
                                        TetrisDomains domains){
        // For every position that the piece we placed takes we check if
        // it is in the domains of the other pieces we remove it
        for(OrderedPair op: value){
            // Go through every other domain
            for(int i = 0; i < domains.domains.size(); i++){
                // If we are looking at the piece that we placed just skip it
                if(i == skip) continue;
                ArrayList<ArrayList<OrderedPair>> pieceDomain = domains.domains.get(i);

                // Set up an iterator to simply delete
                Iterator<ArrayList<OrderedPair>> iter = pieceDomain.iterator();
                while (iter.hasNext()) {
                    ArrayList<OrderedPair> pos = iter.next();
                    // If one of the ordered pairs of the piece that we placed
                    // is in domain of another piece
                    // We remove that part of the domain from the piece
                    for (OrderedPair checkPos : pos) {
                        if (checkPos.equals(op)) {
                            iter.remove(); // Properly remove while iterating
                            break;
                        }
                    }
                }

                // If the domain is empty, forward checking fails
                if (pieceDomain.isEmpty()) return false;
            }
        }
        // No domain is empty
        return true;
    }

    /**
     * Returns the index of the variable that has the smallest domain
     * @param domains - domains of variables that we find the minimum size of
     * @param piecesAdded - set of pieces that have already been placed (don't include in mrv)
     * @return - index of the smallest sized domain piece
     */
    private static int minimumRemainingValues(TetrisDomains domains, HashSet<Integer> piecesAdded){
        int min_index = 0;
        // Move past all of the pieces that we have already placed
        while(min_index < domains.domains.size() &&  piecesAdded.contains(min_index)){
            min_index++;
        }
        // If we reached the end of the list everything is placed
        if(min_index >= domains.domains.size()) return min_index;

        // Find the minimum index
        for(int i = min_index+1; i < domains.domains.size(); i++){
            ArrayList<ArrayList<OrderedPair>> min_domain = domains.domains.get(min_index);
            ArrayList<ArrayList<OrderedPair>> curr_domain = domains.domains.get(i);

            if(min_domain.size() > curr_domain.size() && !piecesAdded.contains(i))
                min_index = i;
        }

        return min_index;
    }
}
