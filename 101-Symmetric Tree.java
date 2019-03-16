Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following [1,2,2,null,3,null,3] is not:
    1
   / \
  2   2
   \   \
   3    3
Note:


Bonus points if you could solve it both recursively and iteratively.


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 思路1
// 分治法：怎么判断一棵树是对称树？

// 该节点的左子树与右子树成镜像关系。
// 那么，如何判镜像是否成立？

// 两颗子树的根节点的值相等
// 一棵子树的左子树与另一子树的右子树成镜像关系
// 一棵子树的右子树与另一子树的左子树成镜像关系
// 分析到此很显然可以用分治法求解，时间复杂度O(n)，空间复杂度O(n)


class Solution {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left,root.right);
    }

    public boolean isMirror(TreeNode left,TreeNode right){
    	if(left == null || right == null)//退出条件，当其中一个是null时，若另一个也是null返回true，否则返回false
    		return left == right;
    	if(left.val != right.val)
    		return false;
    	return isMirror(left.left,right.right) && isMirror(left.right,right.left);
    }
}



// 思路2
// 采用层次遍历的方法，借助队列，每次出队需要比较的（处于对称位置上的）两个节点，判断值是否相等，如果相等，则继续将它们的左右子节点入队（这里的顺序要使对称位置的节点在队列中相邻），直到队列为空，说明树是对称的。
// 时间复杂度O(n)，空间复杂度O(n)

class Solution{
public boolean isSymmetric(TreeNode root) {
    if(root == null)
        return true;
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root.left);
    q.add(root.right);
    while (!q.isEmpty()) {
        TreeNode t1 = q.poll();
        TreeNode t2 = q.poll();
        if (t1 == null && t2 == null) continue;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        q.add(t1.left);
        q.add(t2.right);
        q.add(t1.right);
        q.add(t2.left);
    }
    return true;
}
}

