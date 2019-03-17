Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false
Follow up:
Could you do it without using any loop / recursion?


# 思路1
使用while循环，当n>3时进入循环。首先若一个数不是3的倍数，那么一定不是3的幂，这一点通过n % 3来判断。如果是3的倍数，那么将其/3，继续之前的判断。当退出循环时判断此时的n是否为3或1（1也是3的幂）。
时间复杂度O(n)

class Solution {
    public boolean isPowerOfThree(int n) {
        while(n > 3){
        	if(n % 3 != 0){
        		return false;
        	}
        	n /= 3;
        }
        return n==3 || n==1;
    }
}



public class Solution {
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }
}


# 思路2
无循环无递归解法：
在int范围内，判断那个最大的3的幂 ，是否是n的倍数...

public class Solution {
	public boolean isPowerOfThree(int n) {
	    // 1162261467 is 3^19,  3^20 is bigger than int  
	    return ( n>0 &&  1162261467%n==0);
	}
}