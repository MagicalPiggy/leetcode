In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

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
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	int exitsNum = 0;
        	for (int i = 0; i < size; i++) {
        		TreeNode current = queue.poll();
        		// 判断两个目标节点是否在同一层
        		if (current.val == x || current.val == y) exitsNum++;
        		if (exitsNum == 2) return true;
        		// 判断两个目标节点是否为兄弟
        		if (current.left != null && current.right != null) {
        			if ((current.left.val == x && current.right.val == y) ||
        				(current.right.val == x && current.left.val == y))
        			return false;
        		}
        		if (current.left != null) queue.offer(current.left);
        		if (current.right != null) queue.offer(current.right);        		
        	}
        }
        return false;
    }
}