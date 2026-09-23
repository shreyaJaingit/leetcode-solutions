class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
    for (int i = 0; i < nums.length; i++) {
        totalSum += nums[i];
    }
    if (x > totalSum) {
    return -1;
}

    int target = totalSum - x;

    int left = 0;
    int sum = 0;
    int maxLength = -1;

    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];
    
    while (sum > target) {
        sum -= nums[left];
        left++;
    }
    if(sum == target) {
        int currentLength = right - left + 1;
        maxLength = Math.max(currentLength, maxLength);
    }
    }
    if (maxLength == -1) {
        return -1;
    }
    return nums.length - maxLength;
    }
}