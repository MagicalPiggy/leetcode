# 题目描述
给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

示例 1:
给定的树 s:
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614203738731.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

示例 2:
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614203853817.png)

# 思路
类似[leetcode-100 Same Tree（相同的树）](https://blog.csdn.net/z714405489/article/details/106638184)。只不过这道题的起点不一样，我们先遍历第一棵树s，找到与树t根的值相同的节点作为起点，再进行判断是否为相同的树即可。

## 代码

```java
class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
    	if (s == null) return false;
    	if (s.val == t.val) {
    		if (isSameTree(s, t)) return true;
    	} 
    	return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean isSameTree(TreeNode s, TreeNode t) {
    	if (s == null && t == null) return true;
    	if (s == null || t == null) return false;
    	return (s.val == t.val) && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);  
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614205238365.png)