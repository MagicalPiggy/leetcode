# 题目描述
给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：

该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
如果不存在祖父节点值为偶数的节点，那么返回 0 。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200619235205131.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路
当我们在遍历至某个节点时，若我们可以知道此节点的祖父节点是谁，那么此题就可迎刃而解。
怎么知道呢？在往下递归时把父节点（的值）**p**，与祖父节点（的值）**gp** 传入即可。
传两个值的原因是，p 是为了作为下层的 gp传入， gp是用于判断当前层的祖父节点。

以上面的例子图来说，我们先假设根节点有父节点1与祖父节点1（为了不把根节点统计进去所以假设了两个奇数），那么遍历至根节点的左孩子7时，传入父（6）与祖父（1），接着遍历至7的左孩子2时，传入父（7）与祖父（6），此时就能感知到2的祖父为6了。

## 代码

```java
class Solution {
  public int sumEvenGrandparent(TreeNode root) {
        return helper(root, 1, 1);
    }

    public int helper(TreeNode node, int p, int gp) {
        if (node == null) return 0;
        return helper(node.left, node.val, p) + helper(node.right, node.val, p) + (gp % 2 == 0 ? node.val : 0);
    }
}
```
Runtime: 1 ms, faster than 98.77% of Java online submissions for Sum of Nodes with Even-Valued Grandparent.

Memory Usage: 41.7 MB, less than 43.77% of Java online submissions for Sum of Nodes with Even-Valued Grandparent.