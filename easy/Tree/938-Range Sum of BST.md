# 题目描述

给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

二叉搜索树保证具有唯一的值。


示例 1：

输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
输出：32
示例 2：

输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
输出：23


提示：

树中的结点数量最多为 10000 个。
最终的答案保证小于 2^31。


# 思路
遍历二叉树的基础上，**充分利用二叉搜索树的性质**，左子树中节点一定小于当前节点，那么若当前节点小于范围下界，那么其左子树一定不在范围中，就不必再递归访问；同理，右子树中节点一定大于当前节点，若当前节点大于范围的上界，那么右子树节点一定也不在范围中。

# 代码

```java
class Solution {
    public int rangeSumBST(TreeNode root, int L, int R) {
            if (root == null) return 0; // 返回条件
            if (root.val < L) return rangeSumBST(root.right, L, R); // 排除左分支，只访问右子树
            if (root.val > R) return rangeSumBST(root.left, L, R); // 排除右分支，只访问左子树
            
            // 值在范围中，则左右子树都应该访问
            return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R); // 计算左右子树的符合条件的值之和.
        }
}
```

# 结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200531132918432.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
时间复杂度：O(n)
空间复杂度：O(log n)

# 总结
基础的遍历二叉树问题，辅以对分支的筛选。