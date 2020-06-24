# 题目描述
给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

说明: 叶子节点是指没有子节点的节点。

示例:
给定如下二叉树，以及目标和 sum = 22，

```java
 			  5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1

```
返回:

[
   [5,4,11,2],
   [5,8,4,5]
]

# 思路
先序遍历并且更新经过的路径和应该难度不大，这题的难点在于路径的记录。

这里用一个全局的LinkedList **path**随时更新遍历经过的路径，关键步骤就是**往上层回溯时，删除path的最后一个记录**。

当遍历至叶子节点时，如果路径和与目标相等，则把此时的路径path添加到结果集中（这里要注意得new一个添加进去，否则后面path的更新会影响res中的结果，因为LinkedList是引用数据类型）。

时间复杂度：O(N)
空间复杂度：O(N)
## 代码

```java
class Solution {
	List<List<Integer>> res = new LinkedList<>();
	List<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	if (root == null) return res;
    	dfs(root, 0, sum);
    	return res;
    }

    private void dfs(TreeNode root, int sum, int target) {
    	if (root == null) return;
    	path.add(root.val);
    	sum += root.val;
    	// 叶子节点
    	if (root.left == root.right) {
    		if (sum == target) res.add(new LinkedList<>(path));  			
    	}
    	dfs(root.left, sum, target);
    	dfs(root.right, sum, target);
    	// 回溯
    	path.remove(path.size()-1);
    }
}
```
执行用时：2 ms, 在所有 Java 提交中击败了58.10%的用户
内存消耗：40.3 MB, 在所有 Java 提交中击败了5.26%的用户