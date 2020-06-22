# 题目
【二叉树的中序遍历】
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]

```java
       1
        \
         2
        /
       3
```

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

# 思路1-递归
递归是二叉树遍历种比较常规的做法。
时间复杂度：O(n)
空间复杂度：最差O(n)  平均O(logn)

## 代码

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        inorderVisit(root,list);
        return list;

    }

    public void inorderVisit(TreeNode root,List<Integer> list){
    	if(root != null){
    		inorderVisit(root.left,list);
    		list.add(root.val);
    		inorderVisit(root.right,list);
    	}
    }
}
```
## 提交结果
Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
Memory Usage: 36.2 MB, less than 40.53% of Java online submissions for Binary Tree Inorder Traversal.

# 思路2-非递归
需要结合**栈**来实现。
**while大循环（节点非空 或 栈非空）**：
while小循环中：不停地将根节点的左孩子，以及左孩子的左孩子入栈，直到入栈的是叶子节点，停止入栈循环。

将栈顶节点出栈访问，添加到结果列表，当前指针指向此节点的右孩子进行新一轮的大循环：
叶节点无右孩子将跳过小循环，进行出栈动作（也就是叶节点的父节点出栈...），否则就是右孩子入栈+其左入栈的小循环。

大循环退出条件为当前节点为空，且栈空，说明已经遍历到了最右端的节点。

此法其实原理和递归解法是差不多的，就是由我们自己模拟实现了递归法的压栈与出栈等过程。
时间和空间复杂度都是O（n）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200622220245619.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

## 代码

```java
public class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
```
## 提交结果
Runtime: 1 ms, faster than 35.18% of Java online submissions for Binary Tree Inorder Traversal.
Memory Usage: 36.3 MB, less than 12.51% of Java online submissions for Binary Tree Inorder Traversal.

# 思路3-莫里斯解法（线索二叉树）
1、如果当前结点的左孩子为空，则输出当前结点并将当前结点的右结点作为当前结点。 
2、如果当前结点的左孩子不为空，则从当前结点的左子树找出当前结点的前驱节点： 
如果前驱结点p的右孩子为空，则将p的右孩子设为当前结点；否则，输出当前结点，并将p的右孩子置为空，并将当前当前结点的右孩子置为当前结点 
3、重复1 ，2两步直到当前结点为空
![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9pbWFnZXMwLmNuYmxvZ3MuY29tL2Jsb2cvMzAwNjQwLzIwMTMwNi8xNDIxNDA1Ny03Y2M2NDU3MDZlNzc0MWUzYjVlZDYyYjMyMDAwMDM1NC5qcGc?x-oss-process=image/format,png)

时间复杂度：O(n)。
空间复杂度：O(1)，因为只用了两个辅助指针。
参考：http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html


## 代码

```java
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        while(root != null){
        	if(root.left == null){//如果当前结点的左孩子为空
        		res.add(root.val);//则输出当前结点
        		root = root.right;//并将当前结点的右结点作为当前结点
        	}else{//如果当前结点的左孩子不为空,则从当前结点的左子树找出当前结点的前驱节点： 
        		pre = root.left;
        		while(pre.right != null && pre.right != root){
        			pre = pre.right;//最后pre指向当前结点的前驱结点
        		}
        		if(pre.right == null){//如果前驱结点p的右孩子为空，则将p的右孩子设为当前结点
        			pre.right = root;
        			root = root.left;
        		}else{//否则，输出当前结点，并将p的右孩子置为空，并将当前当前结点的右孩子置为当前结点
        			pre.right = null;
        			res.add(root.val);
        			root = root.right;
        		}
        	}
        }
        return res;
    }
}
```
## 提交结果
Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
Memory Usage: 36 MB, less than 90.56% of Java online submissions for Binary Tree Inorder Traversal.