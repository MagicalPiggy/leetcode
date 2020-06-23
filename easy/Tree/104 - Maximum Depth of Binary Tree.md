# 题目描述
给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

```java
    3
   / \
  9  20
    /  \
   15   7
```
返回它的最大深度 3 。

本题可参考[559 - N叉树的最大深度](https://github.com/MagicalPiggy/leetcode/blob/master/easy/Tree/559%20-%20Maximum%20Depth%20of%20N-ary%20Tree.md)
# 思路1：BFS
求深度，就是求共有几层，自然就想到 **层序遍历** ，关键就是用 **队列** 来保存树的层级顺序，节点出队则将其孩子入队，用for循环来保证将某一层的节点全部出队，此时表明同一层的节点都处理完毕，层数+1。

```java
// BFS
class Solution {
    public int maxDepth(TreeNode root) {
    	if (root == null) return 0;

    	Queue<TreeNode> queue = new LinkedList<>();
    	queue.offer(root);
    	int depth = 0;

    	
    	while (!queue.isEmpty()) {
    		// 该层的节点数
    		int size = queue.size();

    		// 遍历该层所有节点
    		for(int i = 0; i < size; i++) {
    			// 队头出队同时将其子节点都入队
    			TreeNode current = queue.poll();
    			if (current.left != null) queue.offer(current.left);
    			if (current.right != null) queue.offer(current.right);
    		}
    		// 该层节点处理完毕，层数+1
    		depth++;
    	}

    	return depth;	
    }
}
```

执行用时：1 ms, 在所有 Java 提交中击败了17.49%的用户
内存消耗：39.8 MB, 在所有 Java 提交中击败了5.75%的用户

# 思路2：DFS
二叉树最大的深度 = 左右子树的最大深度+1
本问题的子问题，就是求子树的最大深度，由此可以得到递归的解法。

```java
// DFS
class Solution {
    public int maxDepth(TreeNode root) {
    	if (root == null) return 0;
    	return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
```
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：39.9 MB, 在所有 Java 提交中击败了5.75%的用户