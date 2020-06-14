给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：
**一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614222150822.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
通常认为此问题有两种解决方案：自上而下 / 自下而上的方法。
# 思路1：自上而下
第一种方法严格按照平衡二叉树的定义检查树是否平衡：两个子树的高度之差不大于1，并且左子树和右子树也都平衡。使用辅助函数 **calDepth**（），我们可以轻松地编写代码：

## 代码

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return (Math.abs(calDepth(root.left) - calDepth(root.right)) <= 1) && isBalanced(root.left) && isBalanced(root.right);
    }

	private int calDepth(TreeNode root) {
		if (root == null) return 0;
		return Math.max(calDepth(root.left), calDepth(root.right)) + 1;
	}
}
```
对于当前节点根，实际上调用其左侧和右侧子节点的calDepth（）实际上必须访问其所有子节点，因此复杂度为O(N)。我们对树中的每个节点执行此操作，因此isBalanced的总体复杂度将为<font color=red> O(N ^ 2)</font>。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614222553940.png)

# 思路2：自下而上
第二种方法基于DFS。而不是为每个子节点显式调用 **calDepth**（），而是在DFS递归中返回当前节点的**高度**。.
当当前节点（含）的子树平衡时，函数dfsHeight（）返回一个非负值作为高度。否则返回-1。
根据两个子节点的leftHeight和rightHeight，父节点可以检查子树是否平衡，并确定其返回值。

在这种自下而上的方法中，树中的每个节点仅需要访问一次，因为每个节点的高度已经被记录并返回给递归的上一层，上层节点求高度时直接用，而不是如方法1每个节点都得往下求一次高度。

因此，时间复杂度为<font color=red>O(N)</font>，优于第一种解决方案。

## 代码

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfsHeight (root) != -1;
    }

    private int dfsHeight (TreeNode root) {
        if (root == null) return 0;
        
        // 求左子树高度
        int leftHeight = dfsHeight (root.left);
        if (leftHeight == -1) return -1;

        // 求右子树高度
        int rightHeight = dfsHeight (root.right);
        if (rightHeight == -1) return -1;
        
        // 判断当前节点为根的树，是否不平衡
        if (Math.abs(leftHeight - rightHeight) > 1)  return -1;
        
        // 返回当前树的高度
        return Math.max (leftHeight, rightHeight) + 1;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614223303829.png)