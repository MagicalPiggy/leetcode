# 题目描述
给你一棵二叉树，请你返回层数最深的叶子节点的和。
示例：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200618105724464.png)
输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
输出：15


提示：

树中节点数目在 1 到 10^4 之间。
每个节点的值在 1 到 100 之间。

# 思路1：先序遍历
用一个全局变量来记录“最深叶子节点的深度”。
前序遍历，每往下一层，当前深度+1，若是叶子节点，判断其深度是否能更新到全局变量中（若更新，则sum清零重新统计），并且若当前深度已经为最深，则将节点值添加到全局sum中。

## 代码

```java
class Solution {
	int sum = 0;
	int maxDepth = 0;
    public int deepestLeavesSum(TreeNode root) {
    	if (root == null) return 0;
    	travel(root, 0);
    	return sum;
    }

    private void travel(TreeNode root, int depth) {
    	if (root == null) return;

    	// 叶子节点
    	if (root.left == null && root.right == null) {
    		if (depth > maxDepth) {
	    		maxDepth = depth;
	    		sum = 0;
    		}
	    	if (depth >= maxDepth) {
	    		sum += root.val;
	    	}
    	}
    	travel(root.left, depth+1);
    	travel(root.right, depth+1);
    }
}
```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Deepest Leaves Sum.

Memory Usage: 41.6 MB, less than 39.12% of Java online submissions for Deepest Leaves Sum.

# 思路2：层序遍历
层序遍历，每一层都重新统计res，由于最后一层的节点就是目标节点（层数最深的叶子节点），所以最后一层的res就是我们需要的结果。

## 代码

```java
class Solution {
	    public int deepestLeavesSum(TreeNode root) {
        int res = 0, i;
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            for (i = q.size() - 1, res = 0; i >= 0; --i) {
                TreeNode node = q.poll();
                res += node.val;
                if (node.right != null) q.add(node.right);
                if (node.left  != null) q.add(node.left);
            }
        }
        return res;
    }
}
```
Runtime: 3 ms, faster than 48.23% of Java online submissions for Deepest Leaves Sum.
Memory Usage: 40.5 MB, less than 81.93% of Java online submissions for Deepest Leaves Sum.