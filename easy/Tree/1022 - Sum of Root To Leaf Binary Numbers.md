# 题目描述
求二叉树中，根至叶子的二进制数之和！

Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.

Example 1:

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200603230030998.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22

# 思路
- 1.前序遍历是必须的
- 2.定义temp为到达某节点时，所经过路径数字的十进制表示，我们在前序遍历过程中更新temp值。
更新方法：若此节点值为0，表示temp应\*2，否则为\*2+1。例：从1→0到达了 1→0→0时temp为2（二进制：10），此时节点值为0，temp\*2为4（二进制：100）
- 3.只要到达叶子，就将temp值统计到sum（总和）中。
## 代码

```java
class Solution {
	public int sum = 0;
    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        calBinary(root, 0);
        return sum;
    }

    public void calBinary(TreeNode root, int tempNum) {
    	// 递归退出条件
    	if(root == null) return;
		
		// 更新temp的值
		tempNum =  2 * tempNum + root.val;

    	// 判断是否已经到达叶子节点
        if (root.left == null && root.right == null) {
        	sum += tempNum;
    		return;
        }
        calBinary(root.left, tempNum);
        calBinary(root.right, tempNum);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200603230932255.png)

## 改进
在思路不变的基础上，代码还可以更精简一点：
判断是否已经到达叶子节点：root.left == root.right
还有取消全局变量...跟[上一题](https://blog.csdn.net/z714405489/article/details/106496600)很像，今后遍历都尽量采用精简的写法吧~

```java
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return calBinary(root, 0);
    }

    public int calBinary(TreeNode root, int val) {
        if (root == null) return 0;
        val = val * 2 + root.val;
        return root.left == root.right ? val : calBinary(root.left, val) + calBinary(root.right, val);
    }
}
```