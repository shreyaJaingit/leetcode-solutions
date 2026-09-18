class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        //VARIABLES
        int numSub = 0;
        int windowSum = 0;
        //FIRST WINDOW
        for(int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        if (windowSum >= threshold * k) {
            numSub++;
        }
        //SLIDE WINDOW
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i];
            windowSum -= arr[i-k];
            if (windowSum >= threshold * k) {
            numSub++;
        }

        }
        //RETURN NO. OF SUBARRAYS
        return numSub;
    }
}