import java.util.*;

/**
 * Created by yrafiyev on 2017-07-06.
 */
public class Main {

    private void print(LinkedHashMap<String, String> path){
        for(Map.Entry<String, String> d : path.entrySet()){
            System.out.print(d.getKey());
        }
        System.out.println();
    }

    private void printAllPaths(HashMap<String, LinkedList<String>> graph, String from, String to, LinkedHashMap<String, String> visited){
        if(from.equals(to)) print(visited);
        LinkedList<String> neighbors = graph.get(from);
        //System.out.println(neighbors);
        if(neighbors == null) return;
        for(String d: neighbors){
            //Check if any cycle
            if(visited.containsKey(d)) continue;
            visited.put(d, d);
            printAllPaths(graph, d, to, visited);
            visited.remove(d);
        }
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        HashMap <String, LinkedList<String>> graph = new HashMap<String, LinkedList<String>>();
        String from, to;
        from = scan.next();
        to = scan.next();
        scan.nextLine();
        while(scan.hasNext()){
            String value = scan.nextLine();
            Scanner temp = new Scanner(value);
            LinkedList<String> neighbours = new LinkedList<String>();
            String node = temp.next();
            temp.next();
            while (temp.hasNext()){
                neighbours.add(temp.next());
            }
            graph.put(node, neighbours);
        }
        Main main = new Main();
        LinkedHashMap visited = new LinkedHashMap<String, String>();
        visited.put(from, from);
        main.printAllPaths(graph, from, to, visited);

    }
}
