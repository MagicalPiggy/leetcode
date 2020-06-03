Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.

Example 1:
Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22

Note:

The number of nodes in the tree is between 1 and 1000.
node.val is 0 or 1.
The answer will not exceed 2^31 - 1.

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

// 1.判断到达叶子节点
class Solution {
	public int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        calBinary(root, root.val);
        return sum;
    }

    public void calBinary(TreeNode root, int tempNum) {
    	if (root == null) {
    		sum += tempNum;
    		System.println("sum:"+sum);
    		return;
    	}
    	if(root.val == 0) tempNum *= 2;
        else if(root.val == 1) tempNum =  2 * tempNum + 1;
        System.println("tempNum:"+tempNum);
        calBinary(root.left, tempNum);
        calBinary(root.right, tempNum);
    }
}

0 0
1 1
10 2
11 3
100 4
101  5
110  6
111 7
1000 8
1001 9
1010 10

class Solution {
	public int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        calBinary(root, 0);
        return sum;
    }

    public void calBinary(TreeNode root, int tempNum) {
    	// 递归退出条件
    	if(root == null) return;
		
		// 更新temp的值
		tempNum =  2 * tempNum + root.val;

    	// 判断是否已经到达叶子节点
        if (root.left == root.right) {
        	sum += tempNum;
    		return;
        }
        calBinary(root.left, tempNum);
        calBinary(root.right, tempNum);
    }
}

class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return calBinary(root, 0);
    }

    public int calBinary(TreeNode root, int val) {
        if (root == null) return 0;
        val = val * 2 + root.val;
        return root.left == root.right ? val : calBinary(root.left, val) + calBinary(root.right, val);
    }
}