class Solution {
    public char findTheDifference(String s, String t) {
        //Declare frequency array
        int[] freq = new int[26];
        
        //loop through s
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }
        
        //loop through t
        for(int i=0; i<t.length(); i++) {
            char ch = t.charAt(i);
            freq[ch - 'a']--;
            if (freq[ch - 'a'] < 0) {
                return ch;
            }
        }
        return '?';
    }
    }
