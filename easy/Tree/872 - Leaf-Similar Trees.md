# 题目描述
请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200605171127791.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。

如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。

如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。


提示：

给定的两颗树可能会有 1 到 200 个结点。
给定的两颗树上的值介于 0 到 200 之间。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/leaf-similar-trees
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
# 思路
第一感可以想到分别对两棵树进行前序遍历，并将叶子节点记录到list中，最后再比较两个list即可。
除了list，也可以使用String、StringBuilder，都是为了记录下经过的叶子节点。
这些方法固然能解决问题，但是它们都必须完整遍历，而无法中途结束，例如第一个叶子节点就不同了，还得继续往后遍历，这样必然造成耗时过长。
那么要实现中途退出，就得一边遍历一边比较，一旦发现不一致，立即结束。
要保证遍历的连续性，那么就必定要**记录遍历的状态**，这样就能想到使用<font color=red> **栈**</font> 了。
参考[leetcode-589](https://blog.csdn.net/z714405489/article/details/106474012)，N叉树遍历的**迭代**方法，用的就是栈。

## 代码

```java
class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Stack<TreeNode> s1 = new Stack<>(), s2 = new Stack<>();
        s1.push(root1); 
        s2.push(root2);
        while (!s1.empty() && !s2.empty())
        	// 每次深搜找到的叶子值不同则返回false，立即结束程序
            if (dfs(s1) != dfs(s2)) return false;
        // 若其中一方栈空代表其已经遍历完毕，此时若另一方的栈非空说明一定还有叶子节点没有遍历到，那么一定不相似
        return s1.empty() && s2.empty();
    }

    public int dfs(Stack<TreeNode> s) {
    	// 进行一次深搜直到找到叶子节点
        while (true) {
            TreeNode node = s.pop();
            // 出栈并将孩子入栈，若无孩子，则为叶子节点，结束这次搜索
            if (node.right != null) s.push(node.right);
            if (node.left != null) s.push(node.left);
            if (node.left == null && node.right == null) return node.val;
        }
    }
}
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200605174249781.png)