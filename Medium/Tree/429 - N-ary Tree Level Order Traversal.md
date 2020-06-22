# 题目描述
给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。

例如，给定一个 3叉树 :
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200623005927445.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
返回其层序遍历:

```java
[
     [1],
     [3,2,4],
     [5,6]
]
```

说明:

树的深度不会超过 1000。
树的节点总数不会超过 5000。

# 思路
参考[107 - 二叉树的层次遍历 II](https://github.com/MagicalPiggy/leetcode/blob/master/easy/Tree/107%20-%20Binary%20Tree%20Level%20Order%20Traversal%20II.md)
依然用队列来实现。节点出队则将其孩子入队，用for循环来保证将某一层的节点全部出队，此时表明同一层的节点都处理完毕，将此层节点值添加到结果集合中。

```java
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
    	List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	List<Integer> levelRes = new LinkedList<>();       	
        	for (int i = queue.size(); i > 0; i--) {
        		Node current = queue.poll();
        		levelRes.add(current.val);
        		// 将子节点入队
        		for (Node child : current.children) queue.offer(child);
        	}
        	res.add(levelRes);
        } 
        return res;
    }
}
```
Runtime: 2 ms, faster than 86.08% of Java online submissions for N-ary Tree Level Order Traversal.
Memory Usage: 40.5 MB, less than 40.87% of Java online submissions for N-ary Tree Level Order Traversal.