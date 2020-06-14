# 题目描述
给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。


Example:

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200608102358524.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
## 思路
充分利用二叉搜索树的性质，对树进行中序遍历，等到的是一个升序的序列，那么镜像的中序遍历，得到的就是降序的序列。我们只需要将其依次累加即可。

## 代码

```java
class Solution {
	public int temp = 0;
    public TreeNode convertBST(TreeNode root) {
        travel(root);
        return root;
    }

    private void travel(TreeNode root) {
    	if (root == null) return;
    	travel(root.right);
    	root.val += temp;
    	temp = root.val;
    	travel(root.left);
    }
}  
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020060810313964.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)