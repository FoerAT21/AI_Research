import java.util.ArrayList;

public class BacktrackingSearch {

    public static TetrisDomains backtrack(final TetrisDomains domains){
        if(domains.domainsAllSet()) return domains;

        int mrv = minimumRemainingValues(domains);

        for(ArrayList<OrderedPair> domain : domains.domains.get(mrv)){
            System.out.println(domain);
        }

        return null;
    }

    private static int minimumRemainingValues(TetrisDomains domains){
        int min_index = 0;
        for(int i = 1; i < domains.domains.size(); i++){
            ArrayList<ArrayList<OrderedPair>> min_domain = domains.domains.get(min_index);
            ArrayList<ArrayList<OrderedPair>> curr_domain = domains.domains.get(i);

            if(min_domain.size() > curr_domain.size() && curr_domain.size() > 1)
                min_index = i;
        }

        return min_index;
    }
}
