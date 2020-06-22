# 题目描述
给定一个二叉树，返回它的 **前序** 遍历。
 示例:
 输入: [1,null,2,3]  

```java
   1
    \
     2
    /
   3 
```

输出: [1,2,3]
进阶: 递归算法很简单，你可以通过**迭代**算法完成吗？

# 解法1：基础DFS

```java
class Solution {
	List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return res;
        res.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return res;
    }
}
```

# 解法2：迭代（栈）
参考[N叉树的前序遍历](https://blog.csdn.net/z714405489/article/details/106474012)。
使用栈进行辅助，一个节点出栈时，把其子节点按先右后左的顺序入栈，就能保持出栈顺序与先序的一致。

```java
class Solution {	
    public List<Integer> preorderTraversal(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
    	if (root == null) return res;
    	Stack<TreeNode> stack = new Stack<>();
    	stack.push(root);
    	while(!stack.isEmpty()) {
    		TreeNode current = stack.pop();
    		res.add(current.val);
    		if (current.right != null) stack.push(current.right);
    		if (current.left != null) stack.push(current.left);
    	}
    	return res;
    }
}
```

# 解法3：莫里斯遍历
此方法可以优化空间复杂度。算法不会使用额外空间，只需要保存最终的输出结果。如果实时输出结果，那么空间复杂度是 O(1))。


算法的思路是从当前节点向下访问先序遍历的前驱节点，每个前驱节点都恰好被访问两次。

首先从当前节点开始，向左孩子走一步然后沿着右孩子一直向下访问，直到到达一个叶子节点（也就是当前节点的**中序遍历**前驱节点），所以我们<font color=red> 更新输出 </font>并建立一条伪边 **predecessor.right = root** （下图红色的指针），更新这个前驱的下一个点。如果我们第二次访问到前驱节点，由于已经指向了当前节点，我们移除伪边并移动到下一个顶点。

如果第一步向左的移动不存在，就直接<font color=red> 更新输出  </font> 并向右移动。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200623002557746.png)

```java
class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
    LinkedList<Integer> output = new LinkedList<>();

    TreeNode node = root;
    while (node != null) {
      if (node.left == null) {
        output.add(node.val);
        node = node.right;
      }
      else {
        TreeNode predecessor = node.left;
        while ((predecessor.right != null) && (predecessor.right != node)) {
          predecessor = predecessor.right;
        }

        if (predecessor.right == null) {
          output.add(node.val);
          predecessor.right = node;
          node = node.left;
        }
        else{
          predecessor.right = null;
          node = node.right;
        }
      }
    }
    return output;
  }
}


```
时间复杂度：每个前驱恰好访问两次，因此复杂度是O(N)，其中 N 是节点的个数，也就是树的大小。
空间复杂度：我们在计算中不需要额外空间，空间复杂度为 O(1)。