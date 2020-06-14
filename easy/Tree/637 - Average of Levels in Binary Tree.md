# 题目描述
Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200607113942374.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
# 思路
采用层序遍历，参考[leetcode-559](https://blog.csdn.net/z714405489/article/details/106495173)。
采用**队列**，当同一层节点全部出队后计算平均值。
注意求和要使用double类型的变量来记录。

## 代码

```java
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
        	int size = queue.size();
        	double levelSum = 0.0; 
        	// 将同一层的全部出队
        	for(int i =0; i < size; i++) {
        		TreeNode current = queue.poll();
        		levelSum += current.val;
        	    if(current.left != null) queue.offer(current.left);
        	    if(current.right != null) queue.offer(current.right);
        	}
        	list.add(levelSum / size);
        }
        return list;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200607114220950.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)