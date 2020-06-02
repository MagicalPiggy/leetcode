A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.

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
    public boolean isUnivalTree(TreeNode root) {
        if(root == null) return false;
        return travelTree(root, root.val);
    }

    private boolean travelTree(TreeNode root, int val) {
    	if(root == null) return true;
    	if(root.val != val) return false;
    	return travelTree(root.left, val) && travelTree(root.right, val);     
    }
}