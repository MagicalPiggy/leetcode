// Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

// An input string is valid if:

// Open brackets must be closed by the same type of brackets.
// Open brackets must be closed in the correct order.
// Note that an empty string is also considered valid.

// Example 1:

// Input: "()"
// Output: true
// Example 2:

// Input: "()[]{}"
// Output: true
// Example 3:

// Input: "(]"
// Output: false
// Example 4:

// Input: "([)]"
// Output: false
// Example 5:

// Input: "{[]}"
// Output: true

// "[])"

// 使用堆栈，遍历字符串，取当前元素与栈顶元素比较，若配对，则出栈，否则入栈，最后判断栈若为空说明字符串是括弧匹配的，反之不是
class Solution {
    public boolean isValid(String s) {
        if (s.length()==0) return true;
        Stack<Character> stack = new Stack<Character>();
        int i = 1;
        stack.push(s.charAt(0));
        
        while( i<s.length()){
            
        	if(s.charAt(i) == ')' && stack.peek() == '('){
        		stack.pop();        		
        	}

        	else if(s.charAt(i) == ']' && stack.peek() == '['){
        		stack.pop();
        		
        	}

        	else if(s.charAt(i) == '}' && stack.peek() == '{'){
        		stack.pop();
        		
        	}
            else stack.push(s.charAt(i));
            if (stack.empty()) {stack.push(s.charAt(i+1)); i+=2;}
        	i++;
            
        }
        if(stack.empty()) return true;
        else return false;

    }
}

// 其实没有必要非要把整个字符串遍历完再判断，可以在遍历的过程中一旦发现不匹配，立即 return false 。
// 此外，’(’ ‘{’ ‘[’ 这三个左括号可以无需判断栈顶直接入栈，并且，入栈时直接入栈对应的右括号，那么遍历至下一个元素时（若该元素为右括号），直接出栈并判定出栈元素与当前字符是否相等即可
// --------------------- 
// 作者：Mr.Bean-Pig 
// 来源：CSDN 
// 原文：https://blog.csdn.net/z714405489/article/details/88768030 
// 版权声明：本文为博主原创文章，转载请附上博文链接！

class Solution{
public boolean isValid(String s) {
	Stack<Character> stack = new Stack<Character>();
	for (char c : s.toCharArray()) {
		if (c == '(')
			stack.push(')');
		else if (c == '{')
			stack.push('}');
		else if (c == '[')
			stack.push(']');
		else if (stack.isEmpty() || stack.pop() != c)
			return false;
	}
	return stack.isEmpty();
}
}