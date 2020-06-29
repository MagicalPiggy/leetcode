# 题目描述
给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

示例 1:

输入: [1,2,3]

```java
       1
      / \
     2   3

```

输出: 6
示例 2:

输入: [-10,9,20,null,null,15,7]

```java
   -10
   / \
  9  20
    /  \
   15   7
```

输出: 42

# 思路
每个节点都有一条经过自己的最大路径，我们遍历整棵树，并且比较所有节点的最大路径的和，其中的最大值，就是整棵树的最大路径。

经过某节点的最大路径和 = 本身 + 最大左枝 + 最大右枝
见下图，经过节点20的最大路径为 15 - 20 - 7 - 7

如果最大左枝或最大右枝的和为负数，那么它们对最大路径毫无贡献，需要将它们视为0。

如下图的节点15，它的最大左枝为-3，那么将其视为0。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200629180524925.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
由于求一个节点的路径和，需要知道其孩子节点的情况，因此遍历的策略采用**后序遍历**。

先求递归求最大左枝与最大右枝，然后通过**经过某节点的最大路径和 = 本身 + 最大左枝 + 最大右枝**这个关系求出该节点的最大路径和，并且更新整棵树的最大值。

递归的返回值很重要，应该是 **本身 + 左右枝中的较大值**， 这样才能组成一条较大的枝供上层父节点调用。
如上图，对于节点-10来说，求它的最大右枝时，应该返回的是节点20 + 20的最大右枝，组成了20 - 7 - 7

时间复杂度 O(n)
空间复杂度 O(n)

## 代码

```java
class Solution {
	int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
    	maxPath(root);
    	return max;
    }

    private int maxPath(TreeNode root) {
    	if (root == null) return 0;

    	// 求最大左枝
    	int left = maxPath(root.left);
        if (left < 0) left = 0;

       	// 求最大右枝
    	int right = maxPath(root.right);
    	if (right < 0) right = 0;


    	// 求经过此节点的最大路径 = 本身 + 最大左枝 + 最大右枝
    	int sum = left + right + root.val;
    	// 更新整棵树的最大路径和
    	max = sum > max ? sum : max;

    	// 返回上层： 较长的枝 + 本身
    	return Math.max(left, right) + root.val;
    }
}
```
执行用时：1 ms, 在所有 Java 提交中击败了99.74%的用户
内存消耗：41.8 MB, 在所有 Java 提交中击败了7.69%的用户