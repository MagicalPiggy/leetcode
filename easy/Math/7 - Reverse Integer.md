# 题目描述
给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。

如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。

假设环境不允许存储 64 位整数（有符号或无符号）。



示例 1：

输入：x = 123
输出：321
示例 2：

输入：x = -123
输出：-321
示例 3：

输入：x = 120
输出：21
示例 4：

输入：x = 0
输出：0


提示：

-231 <= x <= 231 - 1



思路
此题要对数字翻转并不难，难点是如何判断整数发生了溢出。
我们在对x模10并加到新值之前，先进行溢出判断，若发生了溢出，在对新值进行逆向操作后已经与原值并不相等，由此判断发生了溢出。

代码
class Solution {
	public int reverse(int x)
	{
	    int result = 0;

	    while (x != 0)
	    {
	        int tail = x % 10;//个位数字
	        int newResult = result * 10 + tail;
	        if ((newResult - tail) / 10 != result) //判断是否溢出
	        { return 0; }
	        result = newResult;
	        x = x / 10;
	    }
	
	    return result;
	}