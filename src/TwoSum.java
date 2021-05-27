import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/two-sum/
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution,
and you may not use the same element twice.
You can return the answer in any order.
Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 */

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap();

        for(int i=0 ; i<nums.length ; i++)
        {
            if(map.containsKey(target-nums[i]))
            {
                result[0]=i;
                result[1]=map.get(target-nums[i]);
                return result;
            }
            else
            {
                map.put(nums[i],i);
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        TwoSum obj = new TwoSum();
        int[] result = obj.twoSum(nums,target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

}
