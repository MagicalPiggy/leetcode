# 题目描述
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612231729249.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路
由题可知，一棵二叉树的<font color=red> 直径 </font>是任意两个结点路径长度中的最大值，但是这条路径不一定从根节点经过，也就是说可能出现在子树中。因此，需要找到**每个节点**的直径的最大值。

计算方式：每个节点的直径 = 左子树深度 + 右子树深度。（根据题意，定义根节点的深度为0）.

那么我们只需要递归求出每个节点的左右子树的深度即可，同时在此过程中更新树直径的最大值。
由于求深度需要自底向上计算，因此使用的是**后序遍历**。

## 代码

```java
public class Solution {
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        // 后序遍历，找到树的最大直径
        maxDepth(root);
        return max;
    }
    
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        // 更新最长直径
        max = Math.max(max, left + right);
        

        // 返回此树深度
        return Math.max(left, right) + 1;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200612233447630.png)