****

# 题目描述
给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

 

提示：

num1 和num2 的长度都小于 100
num1 和num2 都只包含数字 0-9
num1 和num2 都不包含任何前导零 
你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式



# 思路
算法流程： 设定 i，j 两指针分别指向 num1，num2 尾部，模拟人工加法；

计算进位： 计算 carry = tmp / 10，代表当前位相加是否产生进位；
添加当前位： 计算 tmp = n1 + n2 + carry，并将当前位 tmp % 10 添加至 res 头部；
索引溢出处理： 当指针 i或j 走过数字首部后，给 n1，n2 赋值为 0，相当于给 num1，num2 中长度较短的数字前面填 0，以便后续计算。
当遍历完 num1，num2 后跳出循环，并根据 carry 值决定是否在头部添加进位 11，最终返回 res 即可。



## 代码

```java
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while(i >= 0 || j >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            i--; 
            j--;
        }
        if(carry == 1) res.append(1);
        return res.reverse().toString();
    }
}
```

