import java.util.ArrayList;

public class BacktrackingSearch {

    public static TetrisDomains backtrack(final TetrisDomains domains){
        int mrv = minimumRemainingValues(domains);
        if(mrv == domains.domains.size()) return domains;

        ArrayList<ArrayList<OrderedPair>> domain = domains.domains.get(mrv);
        System.out.println(domain);
        for(int j = 0; j < domain.size(); j++){
            TetrisDomains newDomains = new TetrisDomains(domains);

            ArrayList<ArrayList<OrderedPair>> temp = new ArrayList<>();
            temp.add(domain.get(j));

            newDomains.domains.set(mrv, temp);
            forwardCheck(domain.get(j), mrv, newDomains);

            TetrisDomains result = backtrack(newDomains);

            if(result != null) return result;
        }


        return null;
    }

    private static void forwardCheck(ArrayList<OrderedPair> value, int index,
                                     TetrisDomains domains){
        for(OrderedPair op: value){
            for(int i = 0; i < domains.domains.size(); i++){
                if(i == index) continue;
                ArrayList<ArrayList<OrderedPair>> pieceDomain = domains.domains.get(i);
                for(int j = 0; j < pieceDomain.size(); j++){
                    ArrayList<OrderedPair> pos = pieceDomain.get(j);
                    if(pos.contains(op)){
                        pieceDomain.remove(pos);
                        j--;
                    }
                }
            }
        }
    }

    private static int minimumRemainingValues(TetrisDomains domains){
        int min_index = 0;
        while(domains.domains.get(0).size() == 1 && min_index < domains.domains.size()){
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
