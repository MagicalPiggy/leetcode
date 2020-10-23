# 题目描述

请你来实现一个 atoi 函数，使其能将字符串转换成整数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：

如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0 。

提示：

本题中的空白字符只包括空格字符 ' ' 。
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。



示例 1:

输入: "42"
输出: 42
示例 2:

输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
示例 3:

输入: "4193 with words"
输出: 4193
解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
示例 4:

输入: "words and 987"
输出: 0
解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
     因此无法执行有效的转换。
示例 5:

输入: "-91283472332"
输出: -2147483648
解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 
     因此返回 INT_MIN (−231) 。



# 思路1：平推

没有太多技巧，需要注意各种特殊情况处理

1. 去掉前导空格
2. 处理正负号与非数字字符
3. 识别数字，注意整数越界情况。

这里的计算包含乘法与加法，都可能造成溢出。判断溢出不能进行了运算后再判断，因此需要反向考虑。

也就是 res * 10 + digit > Integer.MAX_VALUE 转化为 res > (Integer.MAX_VALUE - digit) / 10 。

## 代码

```java
public  int myAtoi(String s) {
        // 寻找第一个非空字符
        int index = 0;
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }
        // 如果已经到达末尾
        if (index == s.length()) return 0;
        // 第一个非空字符判断
        char first = s.charAt(index);
        boolean isNegative = false;
        // 若不为数字
        if (!Character.isDigit(s.charAt(index))) {
            // 若为'+'
            if (first == '+') {
                index++;
            } else if (first == '-') {
                isNegative = true;
                index++;
            } else return 0;
        }
        // 转为int
        int result = 0;
        for(int i = index; i < s.length(); i++) {
            // 中途遇到非数字需要退出
            if (!Character.isDigit(s.charAt(i))) break;
            int digit = s.charAt(i) - '0';
            // int溢出判断
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return isNegative ?  Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + (digit);
        }
        return isNegative == true? -result : result;
    }
```

# 思路2：DFA
官方解法：<font color=red>确定有限状态机（deterministic finite automaton, DFA）</font >

字符串处理的题目往往涉及复杂的流程以及条件情况，如果直接上手写程序，一不小心就会写出极其臃肿的代码。
因此，为了有条理地分析每个输入字符的处理方法，我们可以使用<font color=red>自动机</font >这个概念：
我们的程序在每个时刻有一个状态 s，每次从序列中输入一个字符 c，并根据字符 c 转移到下一个状态 s'。这样，我们只需要建立一个覆盖所有情况的从 s 与 c 映射到 s' 的表格即可解决题目中的问题。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201023144646255.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70#pic_center)
接下来编程部分就非常简单了：我们只需要把上面这个状态转换表抄进代码即可。

另外自动机也需要记录当前已经输入的数字，只要在 s' 为 in_number 时，更新我们输入的数字，即可最终得到输入的数字。

```java
class Solution {
    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(str.charAt(i));
        }
        return (int) (automaton.sign * automaton.ans);
    }
}

class Automaton {
	// 正负号
    public int sign = 1;
    // 结果
    public long ans = 0;
    // 状态
    private String state = "start";
    // 将表格转化为map
    private Map<String, String[]> table = new HashMap<String, String[]>() {{
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
    }};
	// 根据传入字符对状态进行转换
    public void get(char c) {
    	// 转换后的状态
        state = table.get(state)[get_col(c)];
        if ("in_number".equals(state)) {
            ans = ans * 10 + c - '0';
            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }
	// 判断取哪一列，也就是转换到哪个状态
    private int get_col(char c) {
        if (c == ' ') {
            return 0;
        }
        if (c == '+' || c == '-') {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        return 3;
    }
}
```
这个解法使用long来避免判断溢出。