class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        
        dfs(ans, new StringBuilder(), 0, S);
        return ans;
    }
    
    void dfs(List<String> ans, StringBuilder sb, int startIdx, String S) {
        if (sb.length() == S.length()) {
            ans.add(sb.toString());
            return;
        }
        
        for (int i = startIdx; i < S.length(); i++) {
            if (Character.isLetter(S.charAt(i))) {
                // new branch
                sb.append(Character.toLowerCase(S.charAt(i)));
                dfs(ans, sb, i + 1, S);
                sb.deleteCharAt(sb.length() - 1);
                
                sb.append(Character.toUpperCase(S.charAt(i)));
                dfs(ans, sb, i + 1, S);
                sb.deleteCharAt(sb.length() - 1);
            } else {
                // no new branch, just add directly
                sb.append(S.charAt(i));
                dfs(ans, sb, i + 1, S);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        
    }
}

// 加速版
class Solution {
    public List<String> letterCasePermutation(String S) {
        Set<String> ans=new HashSet<>();
        backtrack(ans,S.toCharArray(),0);
        return new ArrayList<String>(ans);
    }
    
    public void backtrack(Set<String> ans, char[] chars, int index)
    {
        ans.add(new String(chars));
        
        for(int i=index;i<chars.length;i++)
            if(Character.isLetter(chars[i]))
            {
                chars[i]=Character.toLowerCase(chars[i]);
                backtrack(ans,chars,i+1);
                chars[i]=Character.toUpperCase(chars[i]);
                backtrack(ans,chars,i+1);
            }
    }
}