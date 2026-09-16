class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        long[][] dp = new long [n+k][2*k+1];
        for (int i = 0; i <= n+k-1; i++) {
            dp[i][0] = 1;
        }
        
        for (int i = 1; i <= n+k-1; i++) {
            for (int j = 1; j <= 2*k; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % MOD;
            }
        }
        return (int) dp[n+k-1][2*k];
    }
}