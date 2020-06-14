Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

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