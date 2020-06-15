# 题目描述
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。 

给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200615181927954.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
# 思路
题目的用例还不够完整，自己补充一个：

```java
    2
   / \
  4   2
     / \
    3   5
```
这种情况下，如果根节点的子节点，与根节点的值相同，那么还需要往下查找。（例如4并不一定是第二小的）
思路直接写在代码里了。

## 代码

```java
 class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if(root == null || root.left == null ) return -1;//没有最小节点

        //找出候选数，默认就是子节点值，如果子节点值和root值相同，递归，在子树中寻找候选数
        int left = root.left.val;
        int right = root.right.val;
        if(root.left.val == root.val) left = findSecondMinimumValue(root.left);
        if(root.right.val == root.val) right = findSecondMinimumValue(root.right);

        //如果左右候选数都正常，返回较小值就可
        if(left != -1 && right != -1){
            return Math.min(left, right);
        }
        //如果候选数有-1，说明整个子树中没有可供候选的数
        if(left != -1){
            //左子树正常，答案就是左边的候选数
            return left;
        }else{
            //右子树正常，返回答案
            //或者右子树也没有候选数，返回-1，即right
            return right;
        }
    }
}
```