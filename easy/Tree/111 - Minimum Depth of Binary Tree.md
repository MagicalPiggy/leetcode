# 题目描述
给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

说明: 叶子节点是指没有子节点的节点。

示例:

给定二叉树 [3,9,20,null,null,15,7],

```java
    3
   / \
  9  20
    /  \
   15   7
```

返回它的最小深度  2.

# 思路
往下遍历时深度+1，到达叶子节点时，更新最小的深度即可。

## 代码

```java
class Solution {
	int minDepth = Integer.MAX_VALUE; 
    public int minDepth(TreeNode root) {
    	if (root == null) return 0;
    	preOrder(root, 0);
    	return minDepth;
    }

    private void preOrder(TreeNode root, int depth) {
    	if (root == null) return ;
    	depth++;
    	if (root.left == null && root.right == null) {
    		minDepth = Math.min(depth, minDepth);
    		return;
    	}
    	preOrder(root.left, depth);
    	preOrder(root.right, depth);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020061615584820.png)