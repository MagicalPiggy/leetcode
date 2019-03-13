// Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

// Example 1:

// Input: [3,0,1]
// Output: 2
// Example 2:

// Input: [9,6,4,2,3,5,7,0,1]
// Output: 8
// Note:
// Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?


// 思路
// 第一感是先排序，但是用排序的话，平均时间复杂度最好只能是O(nlgn)。
// 要在线性复杂度内运行，那么只能遍历常数次。

// 假设数组内的数字没有缺失，那么可以从传入的数组大小就能得到期望的元素之和。
// 以Example 1来说明——Input: [3,0,1]
// nums.length为3，期望的长度应该是4(因为少了一个数嘛)，那么期望和可通过等比数列之和求出：
// （0+3）*4/2=6 ，那么再用这个期望和，减去实际数组内所有元素，就能得到这个缺失的值：
// 6-0-1-2=2
// Output: 2

// 同理，再看Example 2——Input: [9,6,4,2,3,5,7,0,1]
// nums.length为9，期望长度为10，期望和为(0+9)*10/2=45，再用45减去实际数组内所有元素，就能得到这个缺失的值：45-0-1-…-9=8
// Output: 8

// 采用上述方法，时间复杂度为O(n)，空间复杂度为O(1)，符合题目要求

class Solution {
    public int missingNumber(int[] nums) {
    	int n = nums.length;
        int a = n*(n+1)/2; //期望和
        for(int i=0;i<n;i++){
        	a -= nums[i]; //减去所有元素，得到的结果就是缺失值
        }
        return a;
    }
}