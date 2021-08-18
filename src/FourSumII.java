
/*
https://leetcode.com/problems/4sum-ii/

Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
Example 1:
Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
Example 2:
Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1
 */

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        return kSumCount(new int[][]{nums1,nums2,nums3,nums4});
    }

    public int kSumCount(int[][] list)
    {
        Map<Integer, Integer> hashMap = new HashMap();
        addToHashMap(list,hashMap,0,0);
        return countComplement(list,hashMap, list.length/2,0);
    }

    public void addToHashMap(int[][] list, Map<Integer,Integer> hashMap, int index, int sum)
    {
        if(index == list.length/2)
            hashMap.put(sum, hashMap.getOrDefault(sum, 0)+1);
        else
            for(int val : list[index])
                addToHashMap(list,hashMap,index+1,sum+val);
    }

    public int countComplement(int[][] list, Map<Integer, Integer> hashMap, int index, int complement)
    {
        if(index == list.length)
            return hashMap.getOrDefault(complement,0);

        int count = 0;
        for(int val : list[index])
        {
            count += countComplement(list,hashMap,index+1,complement-val);
        }
        return count;
    }

    public static void main(String[] args)
    {
        FourSumII obj = new FourSumII();
        int[] num1 = new int[]{1,2};
        int[] num2 = new int[]{-2,-1};
        int[] num3 = new int[]{-1,2};
        int[] num4 = new int[]{0,2};
        Assert.assertEquals(2,obj.fourSumCount(num1,num2,num3,num4));
    }
}
