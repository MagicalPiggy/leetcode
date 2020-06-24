# 题目描述
给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。

返回移除了所有不包含 1 的子树的原二叉树。

( 节点 X 的子树为 X 本身，以及所有 X 的后代。)

示例1:
输入: [1,null,0,0,1]
输出: [1,null,0,null,1]

解释: 
只有红色节点满足条件“所有不包含 1 的子树”。
右图为返回的答案。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200624151942229.png)
示例2:
输入: [1,0,1,0,0,0,1]
输出: [1,null,1,null,1]
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200624151957355.png)
示例3:
输入: [1,1,0,1,1,0,1,0]
输出: [1,1,0,1,1,null,1]
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200624152017526.png)
说明:

给定的二叉树最多有 100 个节点。
每个节点的值只会为 0 或 1 。

# 思路
剪掉一棵子树的条件是“子树不包含1”，判断是否该剪枝，只需要判断这课树是否含“1”。
那么可以将问题分解为：
一棵树包含“1” = 根节点含“1”或左子树含“1”或右子树“含1”
依照上面的关系就能通过 **后序遍历** 写出 **自下而上** 的解决方案。先判断左右子树是否含1，最后通过根节点的值判断整棵树是否含1。剪枝动作可以在判断完左右子树后就执行。

时间复杂度：O(n)  每个节点只访问一遍
空间复杂度：O(n)  递归时使用栈的开销
```java
class Solution {
    public TreeNode pruneTree(TreeNode root) {
    	return hasOne(root) ? root : null;
    }

    private boolean hasOne(TreeNode root) {
    	if (root == null) return false;
    	// 递归判断左右子树是否含1
    	boolean left = hasOne(root.left);
    	boolean right = hasOne(root.right); 

    	// 执行剪枝
    	if (!left) root.left = null;
    	if (!right) root.right = null;

    	return root.val == 1 || left || right;
    }
}
```
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：37.6 MB, 在所有 Java 提交中击败了8.33%的用户


如果追求极度的**精简**，代码可以写成这样，逻辑是一样的：

```java
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return (root.val == 1 || root.left != null || root.right != null) ? root : null;
    }
}
```