// Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

// Note: For the purpose of this problem, we define empty string as valid palindrome.

// Example 1:

// Input: "A man, a plan, a canal: Panama"
// Output: true
// Example 2:

// Input: "race a car"
// Output: false

//思路1
//首先要把无关字符删除，可以用正则表达式来匹配并删除，由于题目说明忽略大小写，接下来将字符串中的字符全部转化为小写。讲此字符串反转后作为一个新串，接下来比较新旧两个字符串即可。
public class Solution {
    public boolean isPalindrome(String s) {
    	if (s.isEmpty()) {
        	return true;
        }
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();//删除所有的：非字母数字字符，并讲所有的大写字母转换为小写字母
        String rev = new StringBuffer(actual).reverse().toString();//将字符串进行反转
        return actual.equals(rev);//比较
    }
}

//思路2
//双指针，一首一尾，在确保指针指向的字符为字母或数字的前提下，判断各自指向的字符（转为小写后）是否相等。由于无需进行字符匹配，所以效率上比思路1要高。
public class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
        	return true;
        }
        int head = 0, tail = s.length() - 1;//首尾指针
        char cHead, cTail;
        while(head <= tail) {
        	cHead = s.charAt(head);//更新字符
        	cTail = s.charAt(tail);
        	if (!Character.isLetterOrDigit(cHead)) {
        		head++;//若首指针指向的字符非字母或非数字，则指针后移
        	} else if(!Character.isLetterOrDigit(cTail)) {
        		tail--;//若尾指针指向的字符非字母或非数字，则指针前移
        	} else {
        		if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) {
        			return false;//回文检测不通过
        		}
        		head++;//移动指针，继续检测
        		tail--;
        	}
        }//while
        
        return true;//通过检测
    }
}