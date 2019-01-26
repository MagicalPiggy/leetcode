
// 344. Reverse String
// Write a function that reverses a string. The input string is given as an array of characters char[].

// Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

// You may assume all the characters consist of printable ascii characters.

// Example 1:

// Input: ["h","e","l","l","o"]
// Output: ["o","l","l","e","h"]

// Example 2:

// Input: ["H","a","n","n","a","h"]
// Output: ["h","a","n","n","a","H"]


//思路：直接在原数组上修改，从两端向中间依次交换对称位置上的字符串，运行时间为O(n)

class Solution {
    public void reverseString(char[] s) {
        
       for(int i = 0;i < s.length/2 ;i ++)
    	{	
    		char temp = s[i];
    		s[i] = s[s.length-i-1];
    		s[s.length-i-1] = temp;
    	}
    }
}