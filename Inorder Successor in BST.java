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
    // 思路：
    // 如果當前root.val > p.val --> 往左走，然後ans設為root
    // 如果當前root.val = p.val --> 往右走
    // 如果當前root.val < p.val --> 往右走
    // 如果當前root 是 null || p == null return ans
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ans = null;
        if (root == null || p == null) {
            return ans;
        }
        
        if (root.val > p.val) {
            ans = inorderSuccessor(root.left, p);
            // 如果左子樹還有更小的後繼者，就取左子樹的，不然就取當前root本身，看圖片例子。
            return ans != null ? ans : root;
        } else {
            return inorderSuccessor(root.right, p);
        }
    }
}