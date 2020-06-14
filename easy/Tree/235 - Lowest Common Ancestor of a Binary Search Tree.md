# 题目描述
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200610210631404.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200610210642625.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路
首先要明确分析的对象是<font color=red> 二叉搜索树 </font>，应该充分利用它的性质：**可以明确搜索目标的位置：当前、左子树、右子树**。

那么这一性质，对于**找最近公共祖先**有什么帮助呢？

从这一性质出发，分析一下搜索目标的几种情况：

1. 分居根节点的两侧 （分别在左右子树中）   ->   它俩的最近公共祖先一定是当前节点。    ->  直接返回
2. 都在根节点的同侧（都在某一侧子树中）    ->   它俩的最近公共祖先一定在该侧子树中。 -> 递归搜索子树
3. 其中一个就是根节点                                    ->   它俩的最近公共祖先一定是当前节点。   ->  直接返回

依照上面的思路，即可写出代码~

## 代码

```java
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	// 目标都在左子树
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        // 目标都在右子树
        else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }
        // 目标就是当前节点，或目标分别在左右子树中
        else{
            return root;
        }
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200610213445750.png)