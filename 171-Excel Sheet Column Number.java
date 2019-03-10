// Given a column title as appear in an Excel sheet, return its corresponding column number.

// For example:

//     A -> 1
//     B -> 2
//     C -> 3
//     ...
//     Z -> 26
//     AA -> 27
//     AB -> 28 
//     ...
// Example 1:

// Input: "A"
// Output: 1
// Example 2:

// Input: "AB"
// Output: 28
// Example 3:

// Input: "ZY"
// Output: 701


// 思路：26进制转换为10进制

// class Solution {
//     public int titleToNumber(String s) {
//     	char[] a = s.toCharArray();
    	
//     	int sum = 0;
//     	for(int i = a.length-1,bit = 0 ; i >= 0 ;i --,bit++){
//     		sum+= (a[i]-'A'+1)*Math.pow(26,bit);

//     	}
//     	return sum;
        
//     }
// }

class Solution {
    public int titleToNumber(String s) {
    	char[] a = s.toCharArray();
    	
    	int sum = 0;
    	for(int i = 0;i < a.length; i ++){
    		sum = sum*26 + a[i]-'A'+1 ;

    	}
    	return sum;
        
    }
}