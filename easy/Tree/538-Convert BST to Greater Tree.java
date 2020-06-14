Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13


       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9


       5
      / \
    3    6
   / \    \
  2   4    17
 /        / \ 
1        24   9   17
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
	public int temp = 0;
    public TreeNode convertBST(TreeNode root) {
        travel(root);
        return root;
    }

    private void travel(TreeNode root) {
    	if (root == null) return;
    	travel(root.right);
    	root.val += temp;
    	temp = root.val;
    	travel(root.left);
    }
}          