// Given a non-empty array of integers, every element appears twice except for one. Find that single one.

// Note:

// Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

// Example 1:

// Input: [2,2,1]
// Output: 1
// Example 2:

// Input: [4,1,2,1,2]
// Output: 4

// Input: [5,3,2,3,2,5，4,1,2,1,2]


// Output: 4

//思路：
// 要求O（n）的时间复杂度，那么只能对数组进行一次遍历，因此使用两层循环进行比对的方法不可取。此外，不能使用额外的存储空间，那么只能在原数组上进行修改或者运算。
// 考虑强大的异或运算（^/Xor）：0 Xor 某数 = 某数，某数 Xor 自身=0。
// 再注意到异或运算满足加法结合律和交换律。a⊕b⊕a=a⊕a⊕b=0⊕b=0。
// 那么对整个数组中的数字进行连续的异或运算，根据交换律，可以看成若干对重复数字与自身异或为0后，最后与单独的数字异或，结果即为该数字。
// 例如：Input: [4,1,2,1,2]
// 4⊕1⊕2⊕1⊕2=4⊕0=4

class Solution {
    public int singleNumber(int[] nums) {
        
		int num = 0;
        for (int i = 0; i < nums.length; i++) 
        {
            num = num ^ nums[i];
        }
        return num;

    }
}