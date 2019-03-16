// Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

// Example:

// Input: [-2,1,-3,4,-1,2,1,-5,4],
// Output: 6
// Explanation: [4,-1,2,1] has the largest sum = 6.
// Follow up:

// If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.


//思路
// 遍历数组的过程中求累加和，当某个元素比累加和还大（也就是说，前面的累加和为负数），那么重置累加和（舍弃前面所求的和），改为从该元素开始求和，因为最大子序只可能从当前元素开始产生。
// 设置一个int用来保存整个遍历过程中产生的最大的累加和，每次循环的最后检查累加和是否达到了新高，若是，则更新最大和。
// 这实际上是一种简化的动态规划解法，因为只用一个int来跟踪最大和。

class Solution {
    public int maxSubArray(int[] nums) {
    	int maxSum = Integer.MIN_VALUE;//最大和
    	int sum = 0;//累加和
	    for(int i = 0; i<nums.length; i++){
	    	sum += nums[i];//累加操作

	    	if(nums[i] > sum)//若当前元素比累加和还大，说明前面的和可以舍弃，因为最大子序只可能从当前元素开始产生
	    		sum = nums[i];

	    	if(sum > maxSum)//更新最大和
	    		maxSum = sum ;
    	}
    	return maxSum;

    }
       
}

// class Solution {
//     public int maxSubArray(int[] nums) {
//     	int maxSum = Integer.MIN_VALUE;//最大和
//     	int sum = 0;//累加和
// 	    for(int i = 0; i<nums.length; i++){
// 	    	sum = sum > 0 ? sum +nums[i] : nums[i]; 	    	

// 	    	if(sum > maxSum)//更新最大和
// 	    		maxSum = sum ;
//     	}
//     	return maxSum;

//     }
       
// }