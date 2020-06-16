# 题目描述
给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。

其中，克隆树 cloned 是原始树 original 的一个 副本 。

请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。

 

注意：

你 不能 对两棵二叉树，以及 target 节点进行更改。
只能 返回对克隆树 cloned 中已有的节点的引用。
进阶：如果树中允许出现值相同的节点，你将如何解答？

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616194757222.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路
两棵树同时进行遍历即可，当源树找到对应节点，那么克隆树此时的节点就是目标。

## 代码

```java
class Solution {
	public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
		if (original == null || original == target)
			return cloned;
		TreeNode res = getTargetCopy(original.left, cloned.left, target);
		if (res != null)
			return res;
		return getTargetCopy(original.right, cloned.right, target);
	}
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616195146627.png)