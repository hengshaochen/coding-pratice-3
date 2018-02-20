class Solution {
    public boolean isOneEditDistance(String s, String t) {
        /*
        1. 两个字符串的长度之差大于1，那么直接返回False
        2. 两个字符串的长度之差等于1，那么长的那个字符串去掉一个字符，剩下的应该和短的字符串相同
        3. 两个字符串的长度之差等于0，那么两个字符串对应位置的字符只能有一处不同。
        */
        int diff = Math.abs(s.length() - t.length());
        if (diff > 1) {
            return false;
        } else if (diff == 1) {
            // 把s設為長的那個字串
            if (t.length() > s.length()) {
                // swap s和t, 確保s一定比較長
                String temp = t;
                t = s;
                s = temp;
            }
            for (int i = 0; i < s.length(); i++) {
                StringBuilder sCopy = new StringBuilder(s);
                if (sCopy.deleteCharAt(i).toString().compareTo(t) == 0) {
                    return true;
                }
            }
            return false;
        } else {
            // diff == 0 
            int diff_char = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    diff_char++;
                }
            }
            // 剛好是one edit distance才行, s = "" t = "" 這樣答案是False, 因為edit distance = 0
            return diff_char == 1;
        }
    }
}