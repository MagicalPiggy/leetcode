# 题目描述
计算给定二叉树的所有左叶子之和。

示例：
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020060915591654.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24

# 思路
先序遍历，过程中判断是否为叶子节点。为了判断是否为左边的叶子，递归方法再传一个参数来判断。

## 代码

```java
class Solution {
	private int sum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        travel(root, false);
        return sum;
    }

    private void travel(TreeNode root, boolean isLeft) {
    	if (root == null) return;
    	// 判断是否为叶子节点
    	if (root.left == root.right) {
    		// 判断是否为左边的叶子
    		if (isLeft) sum += root.val;
    		return;
    	}
    	travel(root.left, true);
    	travel(root.right, false);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609160034628.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)