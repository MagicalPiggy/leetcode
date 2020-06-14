# 题目描述
A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.

# 思路
典型遍历问题。。。

## 代码

```java
class Solution {
    public boolean isUnivalTree(TreeNode root) {
        if(root == null) return false;
        return travelTree(root, root.val);
    }

    private boolean travelTree(TreeNode root, int val) {
    	if(root == null) return true;
    	if(root.val != val) return false;
    	return travelTree(root.left, val) && travelTree(root.right, val);     
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200602150821471.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

## 简洁版

```java
    int val = -1;
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        if (val < 0) val = root.val;
        return root.val == val && isUnivalTree(root.left)  && isUnivalTree(root.right);
    }
```