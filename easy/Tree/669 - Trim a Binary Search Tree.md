# 题目描述
给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。


![在这里插入图片描述](https://img-blog.csdnimg.cn/20200606003029604.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200606003053172.png)
# 思路
 修剪BST树，其步骤为：
   若为空树，返回NULL;
   否则：
      1.不正常的节点，先修剪之，若根的值不在[L,R]范围内，则执行如下修剪：
          <font color=red>  若根小于下限L，必然有其左子树结点全部小于L（二叉搜索树的性质）</font>，放弃根和左子树，使右子树的根成为新树的根。这里的做法是递归调用 **trimBST()** 方法，入参为右子树的根，那么右子树也会进行这一步的调整，因为右子树还可能依然不符合要求......最终返回的是符合要求的子树
           大于上限R的情况同理。最后结束时，要么根为空，要么根的值在[L,R]中。
2.正常的节点，递归修剪其孩子

## 代码

```java
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        
        // 不正常的节点，修剪至正常
        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);
        
        // 正常的节点，修剪其孩子
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        
        return root;
    }
}
```

## 错误代码

```java
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
    	check(root, L, R);
    	return root;
    }

    private void check(TreeNode root, int L, int R) {
    	if (root == null) return;

		while (root  && root.val < L )  root = root.right; // 这里只是修改了临时变量（root）所指向的对象，而原树的结构没有改变
		while (root  && root.val > R)  root = root.left;
		
		if (root != null) {
			check(root.left, L, R); 
			check(root.right, L, R); 
		}
    }
}
```