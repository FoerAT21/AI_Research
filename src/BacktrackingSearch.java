import java.util.ArrayList;
import java.util.Iterator;

public class BacktrackingSearch {

    public static TetrisDomains backtrack(final TetrisDomains domains){
        int mrv = minimumRemainingValues(domains);

        // If all domains are set
        if(mrv == domains.domains.size()) return domains;

        ArrayList<ArrayList<OrderedPair>> domain = domains.domains.get(mrv);

        for(int j = 0; j < domain.size(); j++){
            TetrisDomains newDomains = new TetrisDomains(domains);

            ArrayList<ArrayList<OrderedPair>> temp = new ArrayList<>();
            temp.add(domain.get(j));

            newDomains.domains.set(mrv, temp);
            TetrisDomains result = null;
            if(forwardCheck(domain.get(j), mrv, newDomains))
                result = backtrack(newDomains);

            if(result != null) return result;
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
        System.out.println("We placed: " + value);
        for(OrderedPair op: value){
            System.out.println("We are checking if domains contain " + op);
            for(int i = 0; i < domains.domains.size(); i++){
                if(i == skip) continue;
                ArrayList<ArrayList<OrderedPair>> pieceDomain = domains.domains.get(i);

                Iterator<ArrayList<OrderedPair>> iter = pieceDomain.iterator();
                while (iter.hasNext()) {
                    ArrayList<OrderedPair> pos = iter.next();
                    System.out.println("We are checking domain element: " + pos);
                    for (OrderedPair checkPos : pos) {
                        if (checkPos.equals(op)) {
                            System.out.println(pos + " contained " + op + " so we remove it");
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

    private static int minimumRemainingValues(TetrisDomains domains){
        int min_index = 0;
        while(min_index < domains.domains.size() && domains.domains.get(min_index).size() == 1){
            min_index++;
        }

        if(min_index >= domains.domains.size()) return min_index;

        for(int i = 1; i < domains.domains.size(); i++){
            ArrayList<ArrayList<OrderedPair>> min_domain = domains.domains.get(min_index);
            ArrayList<ArrayList<OrderedPair>> curr_domain = domains.domains.get(i);

            if(min_domain.size() > curr_domain.size() && curr_domain.size() != 1)
                min_index = i;
        }

        return min_index;
    }
}
