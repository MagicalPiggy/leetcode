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



树的后序遍历（下图的蓝色箭头） = 树的**镜像**的前序遍历 (下图的红色箭头) 的**逆** 。
二叉树是一种特定的树，当然也符合这个结论。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630142813167.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
我们知道了二叉树前序遍历的迭代解法，就是借助栈，某节点出栈时，<font color=red> 从右到左 </font>将此节点的孩子入栈。
现在变成了<font color=red> 镜像问题 </font>，那么出栈时，<font color=red> 从左到右 </font> 将节点的孩子入栈，最后需要求逆，将结果集进行一次翻转就能得到正确结果了。



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