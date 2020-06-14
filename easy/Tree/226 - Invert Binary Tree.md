# 题目描述
翻转一棵二叉树。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200605194621943.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路
翻转二叉树，子问题是交换左右孩子。
于是解法就是递归地交换每个节点的左右孩子。

## 代码

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
    	doInvert(root);
        return root;
    }

    private void doInvert(TreeNode root) {
    	if (root == null) return;
		// 交换左右孩子
    	TreeNode temp = root.left;
    	root.left = root.right;
    	root.right = temp;
    	
    	// 递归地交换
    	doInvert(root.left);
    	doInvert(root.right);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200605194842459.png)