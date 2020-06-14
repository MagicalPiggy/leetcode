Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

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
	List<String> resultList = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return resultList;
        travel(root, "");
        return resultList;
    }

    private void travel(TreeNode root, String temp) {
    	if (root == null) return;
    	temp += (root.val);
    	// 判断此时是否为叶子节点
    	if (root.left == root.right) {
    		// 添加一次结果
    		resultList.add(temp);
    		return;
    	}
    	else temp += "->";
    	travel(root.left, temp);
    	travel(root.right, temp);
    }
}

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
	    List<String> answer = new ArrayList<String>();
	    if (root != null) searchBT(root, "", answer);
	    return answer;
    }
    private void searchBT(TreeNode root, String path, List<String> answer) {
	    if (root.left == null && root.right == null) answer.add(path + root.val);
	    if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
	    if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
	}
}