// O(m * n)
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }
        
        if (isSame(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    public boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if ( (s == null && t != null) || (s != null && t == null) ) {
            return false;
        }
        
        if (s.val != t.val) {
            return false;
        }
        
        return isSame(s.left, t.left) && isSame(s.right, t.right);
        
    }
}

// O(n)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        StringBuilder s_pre = new StringBuilder();
        StringBuilder t_pre = new StringBuilder();
        preorder(s, s_pre);
        preorder(t, t_pre);
        
        return s_pre.toString().contains(t_pre.toString());
    }
    
    public void preorder(TreeNode s, StringBuilder sb) {
        if (s == null) {
            sb.append("#, ");
            return;
        }
        // 防止input是12, 2這種case, 會變成12, #, #   另一個是2, #, # 這樣2的string就會是12的substring, 所以我們可以用
        // ""把真正的數字範圍括號起來
        sb.append("\"" + s.val + "\", ");
        preorder(s.left, sb);
        preorder(s.right, sb);
    }
}