class Solution {
    public int coinChange(int[] coins, int amount) {
        
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Base case
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = amount + 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {

                // Don't take the coin
                dp[i][j] = dp[i - 1][j];

                // Take the coin
                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        1 + dp[i][j - coins[i - 1]]
                    );
                }
            }
        }

        if (dp[n][amount] > amount) {
            return -1;
        }

        return dp[n][amount];
    }
}