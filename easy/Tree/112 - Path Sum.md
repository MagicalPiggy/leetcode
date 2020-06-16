# 题目描述
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

示例: 
给定如下二叉树，以及目标和 sum = 22，

```java
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1

```

返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。

# 思路
先序遍历，没到达一个节点，用目标-节点值，当到叶子节点进行判断是sum否为0。

## 代码

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        // 叶子节点判断和
        if (root.left == null && root.right == null) {
        	return sum == 0;
        } 
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616153201455.png)