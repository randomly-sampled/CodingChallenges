import java.util.*;

/**
 * Yahoo interview coding challenges
 */
public class Solution {

    // Solution 1
    // Runtime: O(nm) where n = length of sentence and m length of expr
    public static List<Integer> permutations(String sentence, String expr){

        HashMap<Character, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        if(expr.isEmpty()) return ans;

        for(int i = 0; i < expr.length(); i++){
            if(map.get(expr.charAt(i)) != null) map.put(expr.charAt(i), map.get(expr.charAt(i))+1);
            else map.put(expr.charAt(i), 1);
        }

        for (int i = 0; i < Math.min(sentence.length(), expr.length()); i++){
            if(map.get(sentence.charAt(i)) != null) map.put(sentence.charAt(i), map.get(sentence.charAt(i))-1);
        }

        for(int i = 0; i < sentence.length() - expr.length() + 1; i++){

            if(i != 0) {
                char a = sentence.charAt(i + expr.length() - 1);
                if (map.get(a) != null) map.put(a, map.get(a) - 1);
            }

            // Check if it makes permutation
            boolean isAllZero = true;
            for(HashMap.Entry<Character, Integer> entry : map.entrySet()){
                if(entry.getValue() != 0) {
                    isAllZero = false;
                    break;
                }
            }

            if(isAllZero) ans.add(i);
            if(map.get(sentence.charAt(i)) != null) map.put(sentence.charAt(i), map.get(sentence.charAt(i)) + 1);
        }

        return ans;
    }

    // Solution 2
    // Modification to speed up
    // Runtime: O(n+m) where n = length of sentence and m length of expr
    public static List<Integer> permutations2(String sentence, String expr){
        HashMap<Character, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        if(expr.isEmpty()) return ans;

        int countNegative = 0;
        int sum = 0;
        for(int i = 0; i < expr.length(); i++){
            if(map.get(expr.charAt(i)) != null) map.put(expr.charAt(i), map.get(expr.charAt(i))+1);
            else map.put(expr.charAt(i), 1);
        }

        for (int i = 0; i < Math.min(sentence.length(), expr.length()); i++){
            if(map.get(sentence.charAt(i)) != null) map.put(sentence.charAt(i), map.get(sentence.charAt(i))-1);
        }

        for(HashMap.Entry<Character, Integer> entry : map.entrySet()){
            if(entry.getValue() < 0) countNegative++;
            sum += entry.getValue();
        }

        for(int i = 0; i < sentence.length() - expr.length() + 1; i++){

            if(i != 0) {
                char a = sentence.charAt(i + expr.length() - 1);
                if (map.get(a) != null) {
                    if(map.get(a) == 0) countNegative++;
                    map.put(a, map.get(a) - 1);
                    sum--;
                }
            }

            // Check if it makes permutation
            if(sum == 0 && countNegative == 0) ans.add(i);

            if(map.get(sentence.charAt(i)) != null) {
                if(map.get(sentence.charAt(i)) == -1) countNegative--;
                map.put(sentence.charAt(i), map.get(sentence.charAt(i)) + 1);
                sum++;
            }
        }

        return ans;
    }

    // To solve this problem we'll assume that string is a collection of lower case characters
    // This assumption will make our life easier, but solution can be extended without assuming this
    // Runtime: O(nm) where n is the size of set array and m is the max length of string
    public static int numberOfIsomorphs(String [] set){
        Set<String> mapping = new HashSet<>();

        // for each string add its unique mapping
        for(int i = 0; i < set.length; i++){
            StringBuilder str = new StringBuilder();
            char cur = 'a';
            Map<Character, Character> used = new HashMap<>();
            for(int j = 0; j < set[i].length(); j++){
                char b = set[i].charAt(j);
                if(used.containsKey(b)){
                    str.append(used.get(b));
                } else {
                    used.put(b, cur);
                    str.append(cur);
                    cur++;
                }
            }
            mapping.add(str.toString());
        }

        return mapping.size();
    }

    private static boolean isOverlapping(int [] x, int [] y){
        if((x[1] >= y[0] && x[0] <= y[1]) || (y[1] >= x[0] && y[0] <= x[1])) return true;
        return false;
    }

    // Given sorted non-overlapping array of ranges, insert x range and return updated non-overlapping array of ranges
    // Runtime: O(n) where n is number of ranges
    public static int [][] updateRange(int [][] ranges, int [] x){
        List<int []> ans = new ArrayList<>();
        int [] cur = null;

        // If x is smaller than all elements
        if(ranges.length == 0 || x[1] < ranges[0][0]) ans.add(x);

        for(int i = 0; i < ranges.length; i++){
            if(cur != null && isOverlapping(cur, ranges[i])){
                cur[0] = Math.min(cur[0], ranges[i][0]);
                cur[1] = Math.max(cur[1], ranges[i][1]);
            } else if(cur != null){
                ans.add(cur);
                ans.add(ranges[i]);
                cur = null;
            } else if(isOverlapping(x, ranges[i])) {
                cur = new int[2];
                cur[0] = Math.min(x[0], ranges[i][0]);
                cur[1] = Math.max(x[1], ranges[i][1]);
            } else {
                ans.add(ranges[i]);
            }
        }

        // if x > all elements
        if(ranges.length != 0 && x[0] > ranges[ranges.length-1][1]) ans.add(x);

        if(cur != null) ans.add(cur);

        int [][] result = new int[ans.size()][2];
        for(int i = 0; i < ans.size(); i++){
            result[i] = ans.get(i);
        }

        return result;
    }

    // Find number of cycles of size 3
    public static int numberOfThreeCycles(){
        return 0;
    }

    public static void main(String[] args){

        int [][] arr = {{1,2}, {3, 4}, {5, 6}, {7, 8}};
        updateRange(arr, new int[]{7, 10});

        //String [] arr = {"aabbaa", "bbaabb", "abcdz", "yukit", "poiuy", "qwertyuiopasdfghjklzxcvbnm", "poiuytrewqlkjhgfdsamnbvcxz"};
        //System.out.println(numberOfIsomorphs(arr));

        //System.out.println(Arrays.toString(permutations2("abcabkbcab", "").toArray()));
    }
}
