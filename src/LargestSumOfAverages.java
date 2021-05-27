
/*
We partition a row of numbers nums into at most k adjacent (non-empty) groups,
then our score is the sum of the average of each group. What is the largest score we
can achieve? Note that our partition must use every number in nums,
and that scores are not necessarily integers.

Example:
Input:
nums = [9,1,2,3,9]
k = 3
Output: 20
 */

public class LargestSumOfAverages {

    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[] sums = new double[n];
        double[][] dp = new double[k][n];
        for(int i = 0 ; i < n ; i++)
        {
            sums[i] = nums[i]+(i>0 ? sums[i-1] : 0);
            dp[0][i] = sums[i]/(i+1);
        }
        for(int i = 1 ; i<k ; i++)
        {
            for(int j = 0 ; j<n; j++)
            {
                double max = 0;
                for(int l = 0 ; l<j ;l++)
                {
                    double avg = dp[i-1][l]+ (sums[j]-sums[l])/(j-l);
                    max = Math.max(max,avg);
                }
                dp[i][j]=max;
            }
        }
        return dp[k-1][n-1];
    }

    public static void main(String[] args)
    {
        LargestSumOfAverages obj = new LargestSumOfAverages();
        int[] nums = new int[]{9,1,2,3,9};
        int k = 3;
        System.out.println(obj.largestSumOfAverages(nums,k));
    }
}
