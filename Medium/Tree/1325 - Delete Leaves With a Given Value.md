# 题目描述
给你一棵以 root 为根的二叉树和一个整数 target ，请你删除所有值为 target 的 叶子节点 。

注意，一旦删除值为 target 的叶子节点，它的父节点就可能变成叶子节点；如果新叶子节点的值恰好也是 target ，那么这个节点也应该被删除。

也就是说，你需要重复此过程直到不能继续删除。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200624215813784.png)
输入：root = [1,2,3,2,null,2,4], target = 2
输出：[1,null,3,null,4]
解释：
上面左边的图中，绿色节点为叶子节点，且它们的值与 target 相同（同为 2 ），它们会被删除，得到中间的图。
有一个新的节点变成了叶子节点且它的值与 target 相同，所以将再次进行删除，从而得到最右边的图。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200624215831554.png)
输入：root = [1,3,3,3,2], target = 3
输出：[1,3,null,null,2]
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200624215845259.png)
输入：root = [1,2,null,2,null,2], target = 2
输出：[1]
解释：每一步都删除一个绿色的叶子节点（值为 2）。

示例 4：

输入：root = [1,1,1], target = 1
输出：[]

示例 5：

输入：root = [1,2,3], target = 1
输出：[1,2,3]


提示：

1 <= target <= 1000
每一棵树最多有 3000 个节点。
每一个节点值的范围是 [1, 1000] 。


# 思路
由于我们需要删除所有值为 target 的叶子节点，那么我们的操作顺序应当从二叉树的叶子节点开始，逐步向上直到二叉树的根为止。因此我们可以使用递归的方法遍历整颗二叉树，并在<font color=red> 回溯 </font>时进行删除操作。这样对于二叉树中的每个节点，它的子节点一定先于它被操作。这其实也就是二叉树的<font color=red> 后序 </font>遍历。

具体地，当我们回溯到某个节点 root 时，如果 root的左右孩子均不存在（这里有两种情况，一是节点 root的孩子本来就不存在，二是节点 root 的孩子变成了叶子节点并且值为 target，导致其被删除，因为孩子节点一定先于root节点进行操作），并且值为 target，那么我们要删除节点 root，递归函数的返回值为空节点；如果节点 root不需要被删除，那么递归函数的返回值为节点 root本身。

时间复杂度：O(N)
空间复杂度：O(N)

## 代码

```java
class Solution {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        // 回溯：若为叶子节点则删除
        if (root.left == root.right) {
        	if (root.val == target)
        		// 删除
        		return null;
        }
        return root;
    }
    
}
```

执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：39.4 MB, 在所有 Java 提交中击败了100.00%的用户

