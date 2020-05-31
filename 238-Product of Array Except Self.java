Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)


class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[](nums.length);
        res[0] = 1;
        for (int i =1;i < res.length ;i++ ) {//最左端位置，最后结果中没有左半部分乘积
        	res[i] = res[i-1] * nums[i-1];//此时的res[i]记录的为该位置的左半部分的乘积
        }
        int right = 1;//右半部分的乘积，因为最右端的位置，最后结果的构成中：没有右半部分乘积，只有左半部分，所以right从1开始
        for(int i = res.length-1; i >= 0; i--){
        	res[i] *= right;//每个位置需要乘上对应的右半部分的乘积
        	right *= nums[i];//right进行累乘，作为下一个位置的右半部分乘积
        }
        return res;
    }
}


