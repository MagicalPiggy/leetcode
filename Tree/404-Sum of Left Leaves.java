Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

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
	private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        travel(root, false);
        return sum;
    }

    private void travel(TreeNode root, boolean isLeft) {
    	if (root == null) return;
    	// 判断是否为叶子节点
    	if (root.left == root.right) {
    		// 判断是否为左边的叶子
    		if (isLeft) sum += root.val;
    		return;
    	}
    	travel(root.left, true);
    	travel(root.right, false);
    }
}