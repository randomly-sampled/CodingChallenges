import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yrafiyev on 2017-07-07.
 */
public class PocketGems {


    private static boolean isTherePath(String[] zombies, int from, int to, Map<Integer, Integer> visited){
        if(from == to) return  true;
        boolean istherePath = false;
        for(int i = 0; i < zombies.length; i++){
            if(zombies[from].charAt(i) == '1'){
                if(visited.containsKey(i)) continue;
                visited.put(i, i);
                istherePath = isTherePath(zombies, i, to, visited);
               // visited.remove(i);
                if(istherePath) return true;
            }
        }
        return false;
    }

    static int zombieCluster(String[] zombies) {
        Map<Integer, Integer> unClustered = new HashMap<>();
        for(int i = 0; i < zombies.length; i++){
            unClustered.put(i, i);
        }
        Map<Integer, Boolean> clusters = new HashMap<>();
        int howManyClusters = 0;
        while (!unClustered.isEmpty()) {
            Iterator<Map.Entry<Integer, Integer>> iter1 = unClustered.entrySet().iterator();
            Map.Entry<Integer,Integer> entry1 = iter1.next();
            int entry1Key = entry1.getKey();
            clusters.put(entry1Key, true);
            while(iter1.hasNext()){
                Map.Entry<Integer,Integer> entry2 = iter1.next();
                Map<Integer, Integer> visited = new LinkedHashMap();
                visited.put(entry1Key, entry1Key);
                if(isTherePath(zombies, entry1Key, entry2.getKey(), visited)){
                    clusters.put(entry1Key, true);
                    iter1.remove();
                }
            }
            unClustered.remove(entry1Key);
            howManyClusters++;
        }
        return clusters.size();
    }

    public static void main(String[] args){
        //String [] zombies = {"10000", "01000", "00100", "00010", "00001"};
        String [] zombies = {"1100", "1110", "0110", "0001"};
        System.out.println(zombieCluster(zombies));
    }
}
