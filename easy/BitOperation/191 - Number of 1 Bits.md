# 题目描述
这题与 **剑指 Offer 15. 二进制中1的个数** 是相同的。

编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。


示例 1：

输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。

示例 2：
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。


示例 3：
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。

提示：

请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。

在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。


进阶:
如果多次调用这个函数，你将如何优化你的算法？

# 思路1：位运算
涉及到二进制，自然想到**位运算**。通过将目标与1进行**按位与（&）**运算，可以判断最低位上的数字是否为1——
- 若 n \& 1 = 0 ，则 n 二进制 最右（最低）的一位 为 0 ；
- 若 n \& 1 = 1 ，则 n 二进制 最右的一位 为 1 。

在循环中，将目标数字向右移1位，就能判断高一位上的数字是否为1，整个过程中对1的位数进行计数。

时间复杂度 O(log2N)： 循环内部的移位、与、加 等基本运算，占用 都是O(1) ；逐位判断需循环log2N次。
空间复杂度O(1)：res占用常数空间。

## 代码

```java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0){
        	if((n & 1) != 0){
        		res++;
        	}
        	n >>>= 1;
        }
        return res;
    }
}
```

执行用时：1 ms, 在所有 Java 提交中击败了99.31%的用户
内存消耗：36.5 MB, 在所有 Java 提交中击败了100.00%的用户


## 小结
值得注意的是>>与>>>的区别。
“>>”是带符号右移——

 - 正数右移时，低位溢出，高位补0
 - 负数右移时，低位溢出，高位补1

“>>>”是无符号右移——

无论是正数还是负数，右移时低位溢出，高位通通补0。

在本题中，由于需要对整数的二进制表示中的所有1进行统计（包括符号位），那么由于“>>”带符号右移时，在负数的情况下由于符号位补1，会影响1的数量。因此。这里只能使用“>>>”无符号右移。

# 思路2：n \& (n - 1)
(n−1) ： 二进制数字 n最右边的 1 变成 0 ，此 1 右边的 0 都变成 1 。
n \& (n - 1) ： 二进制数字 n 最右边的 1 变成 0 ，其余不变。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200703222749887.png)
执行一次n \& (n - 1) ，就能消灭最右边的一个1（无论是否在最低位）
那么先初始化计数res，进入循环先将计数+1（只要n不为0，肯定有1），然后用n \& (n - 1) 消灭1，最后n为0跳出循环即可！

时间复杂度 O(n)： 循环内部的与、减的基本运算，占用 都是O(1) ；n为2进制中1的数量。
空间复杂度O(1)：res占用常数空间。
## 代码

```java
public class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }
}

```
执行用时：1 ms, 在所有 Java 提交中击败了99.31%的用户
内存消耗：36.6 MB, 在所有 Java 提交中击败了100.00%的用户