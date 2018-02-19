class WordDictionary {
    /** Initialize your data structure here. */
    class TrieNode {
        TrieNode[] child;
        boolean is_word;
        TrieNode() {
            child = new TrieNode[26];
            is_word = false;
        }
    }
    TrieNode root;
    public WordDictionary() {
         root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        
        for (int i = 0; i < word.length(); i++) {
            int index = (int)(word.charAt(i) - 'a');
            
            // 不存在該字母的Node就建立該字母的Node
            if (cur.child[index] == null) {
                cur.child[index] = new TrieNode();
            }
            cur = cur.child[index];
        }
        
        // 最後一個字母的boolean設為true
        cur.is_word = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    // 用trie樹，然後如果遇到.就掃該層26個字母的可能性，例如找b.d 一開始走b，再來走a-z，再來走
    // 搜尋的Time Complexity: O(26*輸入word長度)
    public boolean search(String word) {
        return traverse(word, root, 0);
    }
    
    public boolean traverse(String word, TrieNode cur, int cur_length) {
        if (cur_length == word.length()) {
            if (cur.is_word == true) {
                return true;
            }
            return false;
        }
        
        if (word.charAt(cur_length) == '.') {
            // 遇到. 往下一層走，走下一層不等於null且存在在trie樹的所有子節點，例如找b.d 一開始走b，再來走ba, bb, bc, bd ....。
            for (int i = 0; i < cur.child.length; i++) {
                if(cur.child[i] != null && traverse(word, cur.child[i], cur_length + 1) == true) {
                    return true;
                }
            }
        } else {
            int nextChar = (int)word.charAt(cur_length) - 'a';
            // 如果不是. 就正常往下搜尋即可，直接找對應的nextChar即可。
            if (cur.child[nextChar] != null && traverse(word, cur.child[nextChar], cur_length + 1) == true) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */