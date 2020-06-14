# 题目描述

给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。

 

示例 ：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614161550798.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)


提示：

给定树中的结点数介于 1 和 100 之间。
每个结点都有一个从 0 到 1000 范围内的唯一整数值。


# 思路1
中序遍历二叉树，同时新建一颗二叉树，随着遍历的进行，不断往右孩子中填值。
## 代码

```java
class Solution {
	private TreeNode newTree = new TreeNode();
	private TreeNode index = newTree;
    public TreeNode increasingBST(TreeNode root) {   	
        if (root == null) return null;
        increasingBST(root.left);
        newTree.right= new TreeNode(root.val);
        newTree = newTree.right;
        increasingBST(root.right);
        return index.right;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020060211452745.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
# 思路2
不用新建树，直接修改指针即可。

## 代码

```java
class Solution {
	TreeNode prev=null, head=null;
    public TreeNode increasingBST(TreeNode root) {
        if(root==null) return null;   
        increasingBST(root.left);  
        if(prev!=null) { 
        	root.left=null; // we no  longer needs the left  side of the node, so set it to null
        	prev.right=root; 
        }
        if(head==null) head=root; // record the most left node as it will be our root
        prev=root; //keep track of the prev node
        increasingBST(root.right); 
        return head;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200602114459149.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)