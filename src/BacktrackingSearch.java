/**
 * @author Andrew Foerest
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class BacktrackingSearch {

    public static TetrisDomains backtrack(final TetrisDomains domains, HashSet<Integer> piecesAdded, int verbosity){

        // If all domains are set
        if (piecesAdded.size() == domains.domains.size()){
            if (verbosity >= 0) {
                System.out.printf("\nFinal Piece Domains: \n%s\n", domains);
            }
            return domains;
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

            // Getting the piece placement here
            ArrayList<ArrayList<OrderedPair>> temp = new ArrayList<>();
            temp.add(orderedPairs);

            // We place the piece here
            newDomains.domains.set(mrv, temp);
            piecesAdded.add(mrv);
            TetrisDomains result = null;

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
    private static boolean forwardCheck(ArrayList<OrderedPair> value, int skip, TetrisDomains domains){
        for(OrderedPair op: value){
            for(int i = 0; i < domains.domains.size(); i++){
                if(i == skip) continue;
                ArrayList<ArrayList<OrderedPair>> pieceDomain = domains.domains.get(i);

                Iterator<ArrayList<OrderedPair>> iter = pieceDomain.iterator();
                while (iter.hasNext()) {
                    ArrayList<OrderedPair> pos = iter.next();
                    for (OrderedPair checkPos : pos) {
                        if (checkPos.equals(op)) {
                            iter.remove(); // Properly remove while iterating
                            break;
                        }
                    }
                }

                if (pieceDomain.isEmpty()) return false;
            }
        }
        return true;
    }

    private static int minimumRemainingValues(TetrisDomains domains, HashSet<Integer> piecesAdded){
        int min_index = 0;
        while(min_index < domains.domains.size() &&  piecesAdded.contains(min_index)){
            min_index++;
        }

        if(min_index >= domains.domains.size()) return min_index;

        for(int i = min_index+1; i < domains.domains.size(); i++){
            ArrayList<ArrayList<OrderedPair>> min_domain = domains.domains.get(min_index);
            ArrayList<ArrayList<OrderedPair>> curr_domain = domains.domains.get(i);

            if(min_domain.size() > curr_domain.size() && !piecesAdded.contains(i))
                min_index = i;
        }

        return min_index;
    }
}
