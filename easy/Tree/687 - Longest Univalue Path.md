# 题目描述
给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。

注意：两个节点之间的路径长度由它们之间的边数表示。

示例 1:

输入:

```java

              5
             / \
            4   5
           / \   \
          1   1   5
```

输出:

2


示例 2:

输入:

```java
              1
             / \
            4   5
           / \   \
          4   4   5
```

输出:

2
注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。

# 思路
参考[(Java）leetcode-543 Diameter of Binary Tree（二叉树的直径）](https://blog.csdn.net/z714405489/article/details/106725500)。
依然采用<font color=red> 后序遍历</font> ，用**自下而上**求路径长度并更新最大值的思想，不同之处就是，本题需要先判断值与父节点相同才能计算路径长度。

## 代码

```java
class Solution {
	int longestLength = 0;
    public int longestUnivaluePath(TreeNode root) {
    	if (root == null) return 0;
    	calLength(root, root.val);
    	return longestLength;
    }

	private int calLength(TreeNode root, int target) {
		if (root == null) return 0;
		int left = calLength(root.left, root.val);
		int right = calLength(root.right, root.val);

		// 更新最长长度
		longestLength = Math.max(longestLength, left + right);

		// 返回值相等的路径长度
		if (root.val == target) return Math.max(left, right) + 1 ;
		else return 0;
	}
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616173229470.png)