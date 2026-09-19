class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap <String, ArrayList<String>> map = new HashMap<>();
        for(String s : strs) {
           char[] arr = s.toCharArray();
           Arrays.sort(arr);
           String sorted = new String(arr);
           if(map.containsKey(sorted)) {
             map.get(sorted).add(s);
           } else {
            ArrayList <String> list = new ArrayList <>();
            list.add(s);
            map.put(sorted, list);
        }
        }
        return new ArrayList<>(map.values());
    }
}