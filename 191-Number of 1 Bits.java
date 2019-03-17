// Write a function that takes an unsigned integer and return the number of '1' bits it has (also known as the Hamming weight).

 

// Example 1:

// Input: 00000000000000000000000000001011
// Output: 3
// Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
// Example 2:

// Input: 00000000000000000000000010000000
// Output: 1
// Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
// Example 3:

// Input: 11111111111111111111111111111101
// Output: 31
// Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 

// Note:

// Note that in some languages such as Java, there is no unsigned integer type. In this case, the input will be given as signed integer type and should not affect your implementation, as the internal binary representation of the integer is the same whether it is signed or unsigned.
// In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3 above the input represents the signed integer -3.
 

// Follow up:

// If this function is called many times, how would you optimize it?


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

// 值得注意的是>>与>>>的区别。
// “>>”是带符号右移——

// 正数右移时，低位溢出，高位补0
// 负数右移时，低位溢出，高位补1
// “>>>”是无符号右移——

// 无论是正数还是负数，右移时低位溢出，高位通通补0。

// 在本题中，由于需要对整数的二进制表示中的所有1进行统计（包括符号位），那么由于“>>”带符号右移时，在负数的情况下由于符号位补1，会影响1的数量。因此。这里只能使用“>>>”无符号右移。
// --------------------- 
// 作者：Mr.Bean-Pig 
// 来源：CSDN 
// 原文：https://blog.csdn.net/z714405489/article/details/88618100 
// 版权声明：本文为博主原创文章，转载请附上博文链接！