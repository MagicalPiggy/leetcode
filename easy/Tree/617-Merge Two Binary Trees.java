Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:

Input: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
Output: 
Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7

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
	    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
	    	// 返回条件
	    	if(t1 == null && t2 == null) return null;
	    	TreeNode root = new TreeNode();
	    	// 一方为空则取另一方作为新树的节点
	    	if(t1 == null)  root = t2;
			else if(t2 == null)  root = t1;
			// 都非空则值相加，并往下递归生成新节点
			else {
				root.val = t1.val + t2.val;
		    	root.left = mergeTrees(t1.left, t2.left);
		    	root.right = mergeTrees(t1.right, t2.right);
	    	}
	    	return root;
		}
	}