Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.


class Solution {
    public int[] plusOne(int[] digits) {

    	digits[digits.length-1]++;
    	for(int i = digits.length-1;i >0; i--){
    		
    		if(digits[i] == 10){
    			digits[i] = 0;
    			digits[i-1]++;
    		}
    	}

    	if(digits[0] == 10){//最高位需要进位
    		int res[] = new int[digits.length+1];//扩展数组长度
    		Arrays.fill(res, 0);
    		res[0] = 1;
    		return res;

    	}
    	return digits;
	}
}

