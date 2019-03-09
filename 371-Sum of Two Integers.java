// Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

// Example 1:

// Input: a = 1, b = 2
// Output: 3


// Example 2:

// Input: a = -2, b = 3
// Output: 1


//思路

// 很自然的想到用位运算去解决这类问题。
// 先分析一下按位异或：^
// 3  => 011 
// 2  => 010 
// 3^2=> 001 
// 能实现加，但是无法实现进位

// 再看按位与：&
// 3    =>  011 
// 2    =>  010 
// 3&2  =>  010
// 可以反映出第二位产生了进位，并且我们需要将进位产生的1进行左移

// 那么现在有：
// 3 ^2        =>  001 
// (3&2)<<1    =>  100 
// 将以上结果进行 xor ，得到101（5）

// 以此类推，直到不再产生进位，那么将得到最后的结果



class Solution {
    public int getSum(int a, int b) {
      int c; 
      while(b !=0 ) {  //循环结束的标志是b==0；这意味着a & b == 0 ，也就是相加不产生进位
        c = (a&b);  //是否有进位
        a = a ^ b; //实现无进位的相加
        b = (c)<<1;  //进位的结果
      }
      return a;
        
    }
}