# 题目描述
给定一个二叉树，返回所有从根节点到叶子节点的路径。

说明: 叶子节点是指没有子节点的节点。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609201742814.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# 思路
前序遍历二叉树，同时用字符串temp记录路径，每当遍历到叶子节点，则将此时的路径添加到结果集合中，
并且不加“- >”符号。

## 代码

```java
class Solution {
	List<String> resultList = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return resultList;
        travel(root, "");
        return resultList;
    }

    private void travel(TreeNode root, String temp) {
    	if (root == null) return;
    	temp += (root.val);
    	// 判断此时是否为叶子节点
    	if (root.left == root.right) {
    		// 添加一次结果
    		resultList.add(temp);
    		return;
    	}
    	else temp += "->";
    	travel(root.left, temp);
    	travel(root.right, temp);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200609203210711.png)