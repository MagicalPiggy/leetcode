# 题目描述
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200626183259467.png)
示例 1:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出: 3
解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
示例 2:

输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出: 5
解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。


说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉树中。


# 思路
此题是[leetcode-235 Lowest Common Ancestor of a Binary Search Tree （二叉搜索树的最近公共祖先）](https://blog.csdn.net/z714405489/article/details/106674911)这道题的升级版。
回顾一下235这道题，该题特殊之处在于规定的是**二叉搜索树**，可以明确搜索目标的位置。
此题由于是普通的二叉树，只能老老实实向下遍历搜索目标，基于目标的位置，才能按照目标位置情况，返回正确的结果。

由于做出判断需要依赖下层的情况，**后序遍历**再适合不过了。遍历完下层节点后，再通过目标p，q的位置判断最近公共祖先是哪个节点。


- 终止条件：
当越过叶节点，则直接返回 null ；
当 root 等于 p, q ，说明root就是最近公共祖先，直接返回 root ；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200626235932896.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
- 递推过程：
开启递归左子节点，返回值记为 left ；
开启递归右子节点，返回值记为 right ；

- 返回值： 根据 left 和 right ，可展开为四种情况；
1. 当 left 和 right 同时为空 ：说明 root 的左 / 右子树中都不包含 p,q ，返回 null ；
2. 当 left 和 right 同时不为空 ：说明 p, q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
3. 当 left 为空 ，right不为空 ：p,q都不在 root 的左子树中，直接返回 right 。具体可分为两种情况：
（1）p,q 其中一个在 root 的 右子树 中，此时 right 指向 p（假设为 p ）；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200626235858521.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

（2）p,q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200626235818983.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
4. 当 left 不为空 ， right 为空 ：与情况 3. 同理；

情况 1. 可合并至 3. 和 4. 内。

## 代码

```java
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)  return root; // 终止条件
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   return root; // 情况2
        return left != null ? left : right; // 情况1,3,4
    }
}
```

执行用时：7 ms, 在所有 Java 提交中击败了99.90%的用户
内存消耗：42 MB, 在所有 Java 提交中击败了5.71%的用户