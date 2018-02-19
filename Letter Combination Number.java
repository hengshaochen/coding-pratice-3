class Solution {
    public List<String> letterCombinations(String digits) {
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new ArrayList<>();
        
        // corner
        if (digits.length() == 0) {
            return ans;
        }
        
        dfs(0, new StringBuilder(), digits, ans, map);
        
        return ans;
    }
    
    public void dfs(int cur_index, StringBuilder sb, String digits, List<String> ans, String[] map) {
        // base case: 當長度符合時
        if (sb.length() == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        
        // 取得當前digit，例如input:"23"，就可以取得2
        // 用cur_index控制當前走到的digit，然後要把它轉換成map對應的digit
        int cur_digit = digits.charAt(cur_index) - '0';

        // 分支數量是看當前的digit有幾個字母，例如digit = 2時，分枝有abc三種
        for (int i = 0; i < map[cur_digit].length() ; i++) {
            sb.append(map[cur_digit].charAt(i));
            dfs(cur_index + 1, sb, digits, ans, map);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}