# 题目描述

给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。

在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。

返回使每个结点上只有一枚硬币所需的移动次数。

示例 1：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630221837506.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
输入：[3,0,0]
输出：2
解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。

示例 2：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630221856652.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
输入：[0,3,0]
输出：3
解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。

示例 3：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630221917742.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
输入：[1,0,2]
输出：2

示例 4：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630221941256.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
输入：[1,0,0,null,3]
输出：4

提示：

1<= N <= 100
0 <= node.val <= N

# 思路：后序遍历
我们首先遍历节点的孩子，这样可以得知孩子的硬币是多了还是少了。

例如，如果我们从左孩子那里得到“ +3”，则意味着左子树有3个额外的硬币要往外移动。
如果我们从右孩子那里得到“ -1”，则需要将1个硬币移入右子树。
因此，我们将需要移动的步数（用全局变量moves来记录）+4（左边移出3个，右边移入一个）。

然后，我们返回当前子树最终的硬币情况：root.val（根节点中的硬币）+ 3（左）+（-1）（右）-1（根节点中需要留下一个硬币）。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630222213154.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

## 代码

```java
class Solution {
	int moves = 0;
	public int distributeCoins(TreeNode root) {
	  traver(root);
	  return moves;
	}


	private int traver(TreeNode root) {
	  if (root == null) return 0;
	  
	  int left = traver(root.left);
	  int right = traver(root.right);

	  // 记录需要移动的步数
	  moves += Math.abs(left) + Math.abs(right);

	  // 子树的硬币情况，总体来说是多了还是少了
	  return root.val + left + right - 1;
	}
}
```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：39.3 MB, 在所有 Java 提交中击败了33.33%的用户