Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Constraints:

Both of the given trees will have between 1 and 200 nodes.
Both of the given trees will have values between 0 and 200

class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
        s1.push(root1); 
        s2.push(root2);
        while (!s1.empty() && !s2.empty())
        	// 每次深搜找到的叶子值不同则返回false，立即结束程序
            if (dfs(s1) != dfs(s2)) return false;
        // 若其中一方栈空代表其已经遍历完毕，此时若另一方的栈非空说明一定还有叶子节点没有遍历到，那么一定不相似
        return s1.empty() && s2.empty();
    }

    public int dfs(Stack<TreeNode> s) {
    	// 进行一次深搜直到找到叶子节点
        while (true) {
            TreeNode node = s.pop();
            if (node.right != null) s.push(node.right);
            if (node.left != null) s.push(node.left);
            if (node.left == null && node.right == null) return node.val;
        }
    }
}



