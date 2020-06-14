# 题目描述
给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609111616282.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路1
先中序遍历将结果存为list，再遍历一遍，只在相邻的两个值之间求差值（二叉搜索树的性质，决定了相邻的值差值最小），并更新差值的最小值。

## 代码

```java
class Solution {
	public int min = Integer.MAX_VALUE;
	public List<Integer> list = new ArrayList<>();
    public int getMinimumDifference(TreeNode root) {
    	travel(root);
    	return findMin();
    }

    public void travel(TreeNode root) {
    	if(root == null) return;    
        travel(root.left);
        list.add(root.val);
        travel(root.right);
    }

    public int findMin() {
    	for (int i = 1; i < list.size(); i++) {
    		min = Math.min(list.get(i) - list.get(i-1), min);
    	}
    	return min;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609111826227.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
此做法效率不高，做了两次遍历，且空间复杂度为O(n)。

# 改进
不必记录到数组里，因为每次只需要比较相邻的值，那么维持一个索引，始终指向（中序遍历的）前一个值，每次比较当前值和索引值并进行更新即可。 时间复杂度 O(N), 空间复杂度 O(1)。

## 代码

```java
public class Solution {
    int min = Integer.MAX_VALUE;
    Integer prev = null;
    
    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;
        
        getMinimumDifference(root.left);
        
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        // 更新索引
        prev = root.val;
        
        getMinimumDifference(root.right);
        
        return min;
    }
    
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609113047755.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)