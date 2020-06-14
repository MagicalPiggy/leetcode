# 题目描述

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200531142840207.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
注意: 合并必须从两个树的根节点开始。

# 思路
典型的二叉树遍历问题，两棵树同时进行，若一方节点为空而另一方非空，则直接取非空节点作为新树的节点即可；若两边都非空则值相加

# 代码

```java
	class Solution {
	    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
	    	// 返回条件
	    	if(t1 == null && t2 == null) return null;
	    	TreeNode root = new TreeNode();
	    	// 一方为空则取另一方作为新树的节点
	    	if(t1 == null)  root = t2;
			else if(t2 == null)  root = t1;
			// 都非空则值相加，并往下递归生成新节点
			else {
				root.val = t1.val + t2.val;
		    	root.left = mergeTrees(t1.left, t2.left);
		    	root.right = mergeTrees(t1.right, t2.right);
	    	}
	    	return root;
		}
	}
```
# 结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200531143507568.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)