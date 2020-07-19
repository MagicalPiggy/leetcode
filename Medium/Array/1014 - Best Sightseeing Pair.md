# 题目描述
给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。

一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。

返回一对观光景点能取得的最高分。

 

示例：

```java
输入：[8,1,5,2,6]
输出：11
解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
```

提示：

2 <= A.length <= 50000
1 <= A[i] <= 1000

# 思路
### 一、暴力
很容易能想到暴力解法，比较任意两处的观光值从而找出最佳结果：

```java
    public int maxScoreSightseeingPair(int[] A) {
    	int max = 0;
    	for (int i = 0; i < A.length; i++) {
    		for (int j = i+1; j < A.length; j++) {
    			int res = A[i] + A[j] + i - j;
    			max = res > max ? res : max;
    		}
    	}
    	return max;
    }
}
```
这样时间复杂度就是O(n^2)，显然很糟糕。如何优化呢？

### 二、动态规划
若i<j ，求A[i]+A[j]+i−j 的最大值，做一下拆分，即 (A[i]+i)+(A[j]−j) 的最大值
遍历一遍数组，每一个位置都能确定A[j]−j ，并且需要往前找最大的 A[i]+i
用 dp 数组存出现过的 A[i]+i 最大值
dp[i]：第 i 项之前的A[m]+m 的最大值，即从 0 到 i−1 项的 A[m]+m 的最大值

时间复杂度降到了O(n)

```java
class Solution {
    public int maxScoreSightseeingPair(int[] A) {
    	int[] dp = new int[A.length];
    	int res = 0;
    	dp[0] = 0;
    	for (int j = 1; j < A.length; j++) {
    		// 维护A[i] + i
    		dp[j] = Math.max(dp[j - 1], A[j - 1] + j - 1);
    		res = Math.max(res, dp[j] + A[j] - j);
    	}
    	return res;
    }
}
```
### 三、降维优化
当前dp[i] 和 dp[i−1] 之前的项无关——用一个变量存就行，迭代时更新一下
空间复杂度降到了O(1)

```java
class Solution {
    public int maxScoreSightseeingPair(int[] A) {
    	// 维护最佳观光组合的值
    	int max = 0;
    	// 维护遍历过程中A[i] + i的最大值
    	int max_i = 0;

    	for (int j = 1; j < A.length; j++) {
    		max_i = A[j-1] + j -1 > max_i ? A[j-1] + j -1 : max_i;
    		max = A[j] - j + max_i > max ? A[j] - j + max_i : max;
    	}
    	return max;
    }
}
```
执行用时：3 ms, 在所有 Java 提交中击败了95.48%的用户
内存消耗：48.3 MB, 在所有 Java 提交中击败了100.00%的用户