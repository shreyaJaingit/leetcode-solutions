class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            // Start a new subarray
            int remainder = ((num % k) + k) % k;
            newDp[remainder]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] > 0) {
                    int newRemainder =
                        (int)(((long) r * num % k + k) % k);

                    newDp[newRemainder] += dp[r];
                }
            }

            // Add to final answer
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}