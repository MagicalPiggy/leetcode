# 题目描述
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

示例:

输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]

解释:

```java
   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

```

# 思路1：BFS
对二叉树进行层次遍历，采用队列，每次循环将同一层的节点出队，由于入队顺序是从左到右，那么同一层中最后出队的，就是此层最右边的节点。每次将最右边的节点加入结果集即可。

## 代码

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
    	List<Integer> res = new LinkedList<>();
    	if (root == null) return res;
    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	while(!queue.isEmpty()) {
    		// 每次for循环处理一层
    		for (int i = queue.size(); i > 0; i--) {
    			TreeNode curr = queue.poll();
    			// 每层的最右边元素
    			if (i == 1) res.add(curr.val);
    			if (curr.left != null) queue.offer(curr.left);
    			if (curr.right != null) queue.offer(curr.right);
    		}
    	}
    	return res;
    }
}
```
执行用时：1 ms, 在所有 Java 提交中击败了94.95%的用户
内存消耗：38.6 MB, 在所有 Java 提交中击败了5.00%的用户

# 思路2：DFS
我们按照 「根结点 -> 右子树 -> 左子树」 的顺序访问， 就可以保证每层都是最先访问最右边的节点的。

（与先序遍历 「根结点 -> 左子树 -> 右子树」 正好相反，先序遍历每层最先访问的是最左边的节点）

## 代码

```java
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {   // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}
```
执行用时：1 ms, 在所有 Java 提交中击败了94.95%的用户
内存消耗：38 MB, 在所有 Java 提交中击败了5.00%的用户