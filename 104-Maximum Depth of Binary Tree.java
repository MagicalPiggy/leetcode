// Given a binary tree, find its maximum depth.

// The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

// Note: A leaf is a node with no children.

// Example:

// Given binary tree [3,9,20,null,null,15,7],

//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its depth = 3.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//思路：
// 采用递归的解法。
// 定义某棵二叉树的最大深度为其左右子树最大深度中的较大值+1，那么原问题可以分解为两个子问题——求左右子树的最大深度。
// 将子问题继续分解，直到可以直接求出空节点的最大深度（0），最后将子问题的解合并即可。其中求解子问题的过程就是递归的过程。

class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)//递归结束条件
        	return 0;
        else
        {
        	int maxDepthNum = Math.max( maxDepth(root.left),maxDepth(root.right))+1;//返回左右子树的最大深度+1
        	return maxDepthNum;
        }
    }
}