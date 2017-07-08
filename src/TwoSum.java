import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by yrafiyev on 2017-07-07.
 */
public class TwoSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> set = new LinkedList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++){
            int start = i + 1, finish = nums.length - 1;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            while(start < finish){
                if( -nums[i] == (nums[start]+nums[finish])){
                    if((finish < nums.length - 1 && nums[finish] == nums[finish + 1]) ||
                            (start > i + 1 && nums[start] == nums[start - 1])){
                        finish--;
                        start++;
                        continue;
                    }
                    List<Integer> temp = new LinkedList<>();
                    temp.add(nums[i]); temp.add(nums[start]); temp.add(nums[finish]);
                    set.add(temp);
                    finish--;
                    start++;
                } else if( -nums[i] < (nums[start] + nums[finish])){
                    finish--;
                } else {
                    start++;
                }
            }
        }
        return set;
    }

    public static void main(String[] args){
        //String [] zombies = {"10000", "01000", "00100", "00010", "00001"};
        int [] arr = {-1,0,1,2,-1,-4};
        threeSum(arr);
    }
}
