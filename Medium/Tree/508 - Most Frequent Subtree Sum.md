# 题目描述
给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。

你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。

 

示例 1：
输入:

```java
  5
 /  \
2   -3
```

返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。

示例 2：
输入：

```java
  5
 /  \
2   -5
```

返回 [2]，只有 2 出现两次，-5 只出现 1 次。

 

提示： 假设任意子树元素和均可以用 32 位有符号整数表示。

# 思路
这个题目分两部分来解决，第一部分是如何统计元素和，第二部分是如何找到出现次数最多的元素和。
- 如何统计元素和
由题意可知所有的节点的“子树元素和”都必须统计，并且节点的元素和需要通过其孩子的元素和才能得出，因此可以用**后序遍历**来统计所有节点的元素和。
- 如何找到出现次数最多的元素和
  维持一个int变量**max**记录元素和出现**最多的次数**，用一个map记录所有的元素和以及对应出现的次数，每求出一个元素和，就通过map获取这个元素和已经出现的次数，如果超过max，那么我们将结果list（始终保存出现最多的元素和）清空，重新开始记录。只有当一个元素和的次数为max，才能被添加到结果list中。树的遍历结束后，list中的元素和就是出现次数最多的。

## 代码

```java
class Solution {
	int max = 0;
	// 记录所有元素和以及出现的次数
	HashMap<Integer, Integer> map = new HashMap<>();
	// 只记录出现次数最多的元素和
	List<Integer> res = new LinkedList<>();
    public int[] findFrequentTreeSum(TreeNode root) {
    	if (root == null) return new int[0];
    	getSubTreeSum(root);
    	// 处理结果，list转换为数组返回
    	int[] result = new int[res.size()];
    	for (int i = 0; i < result.length; i++) {
    		result[i] = res.get(i);
    	}
    	return result;
    }

    private int getSubTreeSum(TreeNode root) {
    	if (root == null) return 0;

    	// 元素和 = 左子树元素和 + 右子树元素和 + 根节点值
    	int leftSum = getSubTreeSum(root.left);
    	int rightSum = getSubTreeSum(root.right);
    	int subTreeSum = leftSum + rightSum + root.val;

    	// 获取此元素和出现的次数
    	int times = map.getOrDefault(subTreeSum, 0) + 1;
    	// 若找此次数更高
    	if (times > max) {
    		// 更新max
    		max = times;
    		// 重新记录结果数组
    		res.clear();
    	}
    	// 只记录出现次数最多的元素和
    	if(times == max) res.add(subTreeSum);
    	map.put(subTreeSum,times);
    	return subTreeSum;
    }
}
```

执行用时：
6 ms, 在所有 Java 提交中击败了66.76%的用户
内存消耗：40.5 MB, 在所有 Java 提交中击败了100.00%的用户