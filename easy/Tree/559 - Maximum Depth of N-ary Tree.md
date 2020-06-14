# 题目描述
![在这里插入图片描述](https://img-blog.csdnimg.cn/202006122119148.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路1：BFS
求深度，就是求共有几层，自然就想到<font color=red> 层序遍历 </font>，关键就是用<font color=red> 队列 </font>来保存树的层级顺序，节点出队则将其孩子入队，用for循环来保证将某一层的节点全部出队，此时表明同一层的节点都处理完毕，层数+1。
## 代码

```java
// BFS
class Solution {
    public int maxDepth(Node root) {
    	if (root == null) return 0;

    	Queue<Node> queue = new LinkedList<>();
    	queue.offer(root);
    	int depth = 0;

    	
    	while (!queue.isEmpty()) {
    		// 该层的节点数
    		int size = queue.size();

    		// 遍历该层所有节点
    		for(int i = 0; i < size; i++) {
    			// 队头出队同时将其子节点都入队
    			Node current = queue.poll();
    			for(Node child : current.children) {
    				queue.offer(child);
    			}
    		}
    		// 该层节点处理完毕，层数+1
    		depth++;
    	}

    	return depth;	
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200602142248616.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路2：DFS
树最大的深度 = 子树的最大深度+1
本问题的子问题，就是求子树的最大深度，由此可以得到递归的解法。

## 代码

```java
// DFS
class Solution {
    public int maxDepth(Node root) {
    	if (root == null) return 0;
    	int max = 0;
    	for(Node child : root.children) {
    		max = Math.max(maxDepth(child), max);
    	}
    	return max+1;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200602143507895.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)