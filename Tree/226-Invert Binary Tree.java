Invert a binary tree.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
    	doInvert(root);
        return root;
    }

    private void doInvert(TreeNode root) {
    	if (root == null) return;
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
    	doInvert(root.left);
    	doInvert(root.right);
    }
}