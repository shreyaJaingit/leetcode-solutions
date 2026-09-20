class Solution {
    public int reverseDegree(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            int reverseValue = 26 - (s.charAt(i) - 'a');
            answer += reverseValue * (i+1);
        }
        return answer;
    }
}