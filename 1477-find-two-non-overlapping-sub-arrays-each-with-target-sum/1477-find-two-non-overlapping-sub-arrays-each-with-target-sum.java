class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        int minLen = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;

        int left = 0;
        int sum = 0;
        for(int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;

            }
            if (sum == target) {
                int currentLength = right - left + 1;
            

            if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                ans = Math.min(ans, currentLength + best[left-1]);
            }
            minLen = Math.min(minLen, currentLength);
        }
        best[right] = minLen;
        }
    if (ans == Integer.MAX_VALUE) {
        return -1;
    } else {
        return ans;
    }
}
}