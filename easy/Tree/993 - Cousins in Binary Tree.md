# 题目描述
在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。

如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。

我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。

只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020060914262829.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609142639177.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609142652509.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路
返回true的条件：两个节点深度相同，但父节点不同。

既然涉及到深度，就想到层次遍历，用BFS来判断节点是否在同一层。
判断方法：由于BFS每次出队都是同一层的节点，那么此时队内的节点一定也是同一层的，那么在遍历时进行比对即可。每遇到一个目标，计数+1，当计数到达2说明两个目标都在同一层。

此外，节点入队时就判断其两个孩子是否是两个目标，如果是直接返回false，这样就能保证队列里的都是有可能成为“堂兄弟”的节点。

BFS框架：

```java
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	int size = queue.size();

        	for (int i = 0; i < size; i++) {
        		TreeNode current = queue.poll();
        		if (current.left != null) queue.offer(current.left);
        		if (current.right != null) queue.offer(current.right);        		
        	}
        }
```

## 代码

```java
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
    	if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	int exitsNum = 0;
        	for (int i = 0; i < size; i++) {
        		TreeNode current = queue.poll();
        		// 判断两个目标节点是否在同一层
        		if (current.val == x || current.val == y) exitsNum++;
        		if (exitsNum == 2) return true;
        		// 判断两个目标节点是否为兄弟
        		if (current.left != null && current.right != null) {
        			if ((current.left.val == x && current.right.val == y) ||
        				(current.right.val == x && current.left.val == y))
        			return false;
        		}
        		if (current.left != null) queue.offer(current.left);
        		if (current.right != null) queue.offer(current.right);        		
        	}
        }
        return false;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609143639826.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)