Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.

 

Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32

Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23
 

Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.

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
    public int rangeSumBST(TreeNode root, int L, int R) {
            if (root == null) return 0; // 返回条件
            if (root.val < L) return rangeSumBST(root.right, L, R); // 排除左分支，只访问右子树
            if (root.val > R) return rangeSumBST(root.left, L, R); // 排除右分支，只访问左子树
            // 值在范围中，则左右子树都应该访问
            return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R); // 计算左右子树的符合条件的值之和.
        }
}