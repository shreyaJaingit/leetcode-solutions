class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        // Step 1: Store first and last occurrence of each character
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        // Step 2: Find all valid intervals
        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {

            if (last[c] == -1) {
                continue;
            }

            int start = first[c];
            int end = last[c];

            boolean valid = true;

            for (int i = start; i <= end; i++) {

                int currentChar = s.charAt(i) - 'a';

                // This character appeared before our start.
                // Therefore we cannot create a valid substring
                // starting at 'start'.
                if (first[currentChar] < start) {
                    valid = false;
                    break;
                }

                // We must include all occurrences of this character.
                // So extend our interval if necessary.
                end = Math.max(end, last[currentChar]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Step 3: Sort intervals by ending position
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        // Step 4: Greedily choose non-overlapping intervals
        List<String> result = new ArrayList<>();

        int previousEnd = -1;

        for (int[] interval : intervals) {

            int start = interval[0];
            int end = interval[1];

            if (start > previousEnd) {
                result.add(s.substring(start, end + 1));
                previousEnd = end;
            }
        }

        return result;
    }
}
    
