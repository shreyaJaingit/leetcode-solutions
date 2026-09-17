class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int windowSum = 0;

        // FIRST WINDOW
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        int maxSum = windowSum;

        // SECOND WINDOW
        for (int windowEnd = k; windowEnd < nums.length; windowEnd++) {
            windowSum += nums [windowEnd];
            windowSum -= nums [windowEnd - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return (double) maxSum / k; 
        
    }
}