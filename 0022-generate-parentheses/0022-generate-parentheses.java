class Solution {
    List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        
        backtrack("", 0, 0, n); 
        return result;
    }
    void backtrack (String current, int open, int close, int n) {
        if (open == n && close == n) {
            result.add(current);
            return;
        }
        if (open < n) {
            backtrack(current + "(", open + 1, close, n);
        }
        if (close < open) {
            backtrack(current + ")", open, close + 1, n);
        }
    }
    }
