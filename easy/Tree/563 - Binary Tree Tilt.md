# 题目描述
给定一个二叉树，计算整个树的坡度。

一个树的节点的坡度定义即为，该节点左子树的**结点之和**和右子树**结点之和**的差的绝对值。空结点的的坡度是0。

整个树的坡度就是其所有节点的坡度之和。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613211054790.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
注意:

任何子树的结点的和不会超过32位整数的范围。
坡度的值不会超过32位整数的范围。

<font color=red>这道题目很容易读错题，以为是每个节点的左右孩子之差，于是应该补充一个更清晰的用例如下： </font>
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613211606174.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
# 思路
由上面的用例可以清晰的看到，在计算节点1的坡度时，就需要知道其左右子树的节点之和，说明应该<font color=red>自下而上</font>进行求和，因此适合这一特点的遍历应当是<font color=red>后序遍历</font>。

我们遍历至某一节点时，先递归求出其左右子树的和，进而可以求出绝对值差，还能求出当前树的节点和（左+右+自身），并且返回给上一层，实现了自下而上的求和。

## 代码

```java
class Solution {
	int sum = 0;
    public int findTilt(TreeNode root) {
    	travel(root);
    	return sum;											
    }

    // 此方法返回值为传入节点为根的树的节点之和
    private int travel(TreeNode root) {
    	if (root == null) return 0;

    	// 递归求左子树的节点和
        int leftSum = travel(root.left);
        // 递归求右子树的节点和
        int rightSum = travel(root.right);

        //计算该节点的坡度并添加到结果中
        sum += Math.abs(leftSum - rightSum);
       

        return leftSum + rightSum + root.val;

    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200613212530164.png)