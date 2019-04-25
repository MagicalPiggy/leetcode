Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?



class Solution {
    public void rotate(int[] nums, int k) {
        
        k = k%nums.length;//k比数组长度大的情况，每nums.length次循环后数组还原，所以有效的k应该是k%nums.length
        
        
        reverse(nums, nums.length-k, nums.length-1);
        reverse(nums, 0, nums.length-k-1);
        reverse(nums, 0, nums.length-1);
    }

    public void reverse(int[] nums, int start, int end){//用来反转数组中指定的一段
    	for( int temp; start < end ; start++,end--){
    		
    		temp = nums[end];
    		nums[end] = nums[start];
    		nums[start] = temp;
    	}
    }
}