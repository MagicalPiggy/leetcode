# 题目描述
给定一个二叉树，返回它的 后序 遍历。

示例:

输入: [1,null,2,3]  

```java
   1
    \
     2
    /
   3 

```

输出: [3,2,1]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？

# 思路
递归算法就不贴了，是树的题目的基础算法。
至于迭代算法，参考[leetcode-589、590 N-ary Tree Traversal（N叉树遍历）](https://github.com/MagicalPiggy/leetcode/blob/master/easy/Tree/589%26590%20-%20N-ary%20Tree%20%20Traversal.md)中的“N叉树的后序遍历”，换成二叉树的版本，思路是一样的。

在最后反转ArrayList的代码：

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;

		Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
		while(!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);
	        if (root.left != null) stack.push(root.left);
	        if (root.right != null) stack.push(root.right);
        }
        Collections.reverse(res);
        return res;
    }
}
```
或是是使用LinkedList，在添加元素到结果集时，使用addFirst()方法，从list的头部添加元素，最后就不用整体翻转了：

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new LinkedList<>();
		if (root == null) return res;

		Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
		while(!stack.isEmpty()) {
            root = stack.pop();
            // 从列表的头部添加
            res.addFirst(root.val);
	        if (root.left != null) stack.push(root.left);
	        if (root.right != null) stack.push(root.right);
        }
        return res;
    }
}
```

时间复杂度：访问每个节点恰好一次，因此时间复杂度为 O(N)，其中 N 是节点的个数，也就是树的大小。
空间复杂度：取决于树的结构，最坏情况需要保存整棵树，因此空间复杂度为 O(N)。