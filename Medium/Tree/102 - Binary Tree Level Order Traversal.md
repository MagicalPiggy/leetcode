# 题目
给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

示例：
二叉树：[3,9,20,null,null,15,7],

```java
    3
   / \
  9  20
    /  \
   15   7
```
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]

# 思路
参考[429 - N叉树的层序遍历](https://github.com/MagicalPiggy/leetcode/blob/master/Medium/Tree/429%20-%20N-ary%20Tree%20Level%20Order%20Traversal.md)
用队列来实现。节点出队则将其孩子入队，用for循环来保证将某一层的节点全部出队，此时表明同一层的节点都处理完毕，将此层节点值添加到结果集合中。

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	List<Integer> levelRes = new LinkedList<>();
        	for (int i = queue.size(); i > 0; i--) {
        		TreeNode current = queue.poll();
        		levelRes.add(current.val);
        		if (current.left != null) queue.offer(current.left);
        		if (current.right != null) queue.offer(current.right);
        	}
        	res.add(levelRes);
        }
        return res;
    }
}
```
执行用时：1 ms, 在所有 Java 提交中击败了90.67%的用户
内存消耗：39.9 MB, 在所有 Java 提交中击败了5.71%的用户