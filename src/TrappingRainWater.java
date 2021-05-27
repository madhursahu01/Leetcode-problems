
/*
https://leetcode.com/problems/trapping-rain-water/
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped.

 */

public class TrappingRainWater {

    public int trap(int[] A){

        int left = 0;
        int right = A.length-1;

        int leftMax = 0;
        int rightMax = 0;
        int total = 0;

        while(left <= right)
        {
            leftMax = Math.max(A[left],leftMax);
            rightMax = Math.max(A[right],rightMax);
            if(leftMax <= rightMax)
            {
                total += leftMax-A[left];
                left++;
            }
            else
            {
                total += rightMax-A[right];
                right--;
            }
        }
        return total;
    }

    public static void main(String[] args)
    {
        int[] A = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater obj = new TrappingRainWater();
        System.out.println(obj.trap(A));
    }
}
