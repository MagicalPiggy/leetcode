Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

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
	public int min = Integer.MAX_VALUE;
	public List<Integer> list = new ArrayList<>();
    public int getMinimumDifference(TreeNode root) {
    	travel(root);
    	return findMin();
    }

    public void travel(TreeNode root) {
    	if(root == null) return;    
        travel(root.left);
        list.add(root.val);
        travel(root.right);
    }

    public int findMin() {
    	for (int i = 1; i < list.size(); i++) {
    		min = Math.min(list.get(i) - list.get(i-1), min);
    	}
    	return min;
    }
}

public class Solution {
    int min = Integer.MAX_VALUE;
    Integer prev = null;
    
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        
        getMinimumDifference(root.left);
        
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        // 更新索引
        prev = root.val;
        
        getMinimumDifference(root.right);
        
        return min;
    }
    
}