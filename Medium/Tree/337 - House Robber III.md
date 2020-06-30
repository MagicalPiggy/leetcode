# 题目描述
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

示例 1:

输入: [3,2,3,null,3,null,1]

```java
     3
    / \
   2   3
    \   \ 
     3   1
```

输出: 7 
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
示例 2:

输入: [3,4,5,1,3,null,1]

```java
     3
    / \
   4   5
  / \   \ 
 1   3   1
```

输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

# 思路1：递归（自上而下）
先找递归关系——
对于任意一个节点（房屋），有两种选择：
（1）抢当前节点 + 孙子们（下家的下家，最多能抢四家）
（2）要么放弃当前节点，抢儿子们（下家，最多两家）

所以问题归结为计算哪种方案得到的东西更多。

将以上描述写成状态转移式，就是本题的关键：

**rob(root) = Math.max( rob(root.grandson) +root.val, rob(root.son) )**

根据上式，可以写出暴力的自上而下的递归解法：

```java
// 暴力解
// 要么抢当前root+孙子，要么放弃当前，抢儿子
class Solution {
    public int rob(TreeNode root) {
    	if(root == null) return 0;
    	int son = 0;
    	int grandson = 0;
    	if(root.left != null) {
    		son += rob(root.left);
    		grandson += rob(root.left.left) + rob(root.left.right);  		 
    	}
    	if(root.right != null) {
    		son += rob(root.right);
    		grandson += rob(root.right.left) + rob(root.right.right);;   		
    	}
    	return Math.max(grandson + root.val, son);
    }
}
```
执行用时：
585 ms, 在所有 Java 提交中击败了33.80%的用户
内存消耗：39.3 MB, 在所有 Java 提交中击败了33.33%的用户

# 思路2：带备忘录的递归
上面的方法由于进行了许多重复计算，因此耗时当然巨大。
我们可以用备忘录（HashMap）来存储已经计算过的节点和对应的结果，需要计算时先看备忘录中是否已经有记录，省去了重复计算，时间复杂度直接降到O（N）。

## 代码

```java
// 备忘录
class Solution {
	HashMap<TreeNode, Integer> map = new HashMap<>();
    public int rob(TreeNode root) {
    	if(root == null) return 0;
    	if (map.containsKey(root)) return map.get(root);
    	int son = 0;
    	int grandson = 0;
    	if(root.left != null) {
    		son += rob(root.left);
    		grandson += rob(root.left.left) + rob(root.left.right);  
    	}
    	if(root.right != null) {
			son += rob(root.right);
    		grandson += rob(root.right.left) + rob(root.right.right);;  
    	}
    	int res = Math.max(grandson + root.val, son);
    	map.put(root,res);
    	return res;
    }
}
```
执行用时：3 ms, 在所有 Java 提交中击败了59.14%的用户
内存消耗：39.6 MB, 在所有 Java 提交中击败了33.33%的用户

# 思路3：自下而上（无备忘录）
这道题让巧妙的点在于， 还有更漂亮的解法——
重新定义了一个节点的两个状态：

(1) 抢，那么下家不能抢
(2) 不抢，那么下家抢不抢，取决于收益大小

递归函数需要返回的是当前节点的两种状态。
子结点的状态陆续汇报信息给父结点，一层一层向上汇报，最后在根结点汇总值。因此采用**后序遍历**。

```java
class Solution {	
	int rob(TreeNode root) {
		int[] res = dp(root);
		return Math.max(res[0], res[1]);
	} 

	/* 返回⼀个⼤⼩为 2 的数组 arr
	arr[0] 表⽰不抢 root 的话， 得到的最⼤钱数
	arr[1] 表⽰抢 root 的话， 得到的最⼤钱数 */

	int[] dp(TreeNode root) {
		if (root == null)
		return new int[]{0, 0};
		int[] left = dp(root.left);
		int[] right = dp(root.right);
		// 抢， 下家就不能抢了
		int rob = root.val + left[0] + right[0];
		// 不抢， 下家可抢可不抢， 取决于收益⼤⼩
		int not_rob = Math.max(left[0], left[1])+ Math.max(right[0], right[1]);
		
		return new int[]{not_rob, rob};
		}
}
```
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：39.7 MB, 在所有 Java 提交中击败了33.33%的用户


这种解法和上面的思路不⼀样， 修改了递归函数的定义， 略微修改了思路， 使得逻辑⾃洽， 依然得到了正确的答案， ⽽且代码更漂亮。 


这也体现了DP问题的一个特性：「不同定义产⽣不同解法」 。

实际上， 这个解法⽐前面的两种解法运⾏时间要快得多， 虽然算法分析层⾯时间复杂度是相同的。 原因在于此解法没有使⽤额外的备忘录， 减少了数据操作的复杂性， 所以实际运⾏效率会快。