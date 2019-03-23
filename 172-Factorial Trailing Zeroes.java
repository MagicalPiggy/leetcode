Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.

class Solution {
    public int trailingZeroes(int n) {
    	if(n < 0) return 0;
        int trailingResult = trailingRes(n);//1932053504
        int zeroNums = 0;

       while(trailingResult%10 == 0){
        	zeroNums++;
        	trailingResult/=10;
        }
        return zeroNums;
    }

    public int trailingRes(int n){
    	int res = 1;
    	while(n > 0){
    		res *= n;
    		n--;
    	}
    	return res;
    }
}

思路
第一感是先求阶乘，再统计0，但是由于int的限制，当输入的n较大时就无能为力了，那么只能另辟蹊径了。
思考：尾数中的0是如何产生的？
——都是通过2*5（其倍数也能归结为此情况）产生的
那么每个阶乘都可以表示为 n！ = 2 ^ x * 5 ^ y * ......（其中x和y> = 0）
“末尾零数”取决于min（x，y），推理可得min（x，y）总是y，因为2的倍数比5的倍数更频繁出现，所以我们只需要得到y即可

n可以表示为1 .... 5 .... 10 ..... 15 ..... 20 .... 25 .... 5 * i .......... .n（i> 5）
也就是说n! = 1*...5*....10*......15*...20*...25*.......5*i..*n (i > 5)

n / 5 =在上述序列中贡献一个0的组数——因为每一组中都存在一个5的倍数，这个数字能产生1个尾0

n / (5 ^ 2)或n / 5/5=上述序列中贡献两个0的组数（它们的1 个贡献在上面已经被计算过）——因为25可以贡献2个0, 25的倍数同理

n /( 5 ^ 3)或n / 5/5/5=上述序列中贡献三个0的组数（它们的2 个贡献在上面已经被计算过）——因为125可以贡献3个0

n / (5 ^ y) =在上述序列中贡献y个0的组数（它们的y-1 个贡献在上面已经被计算过）
（只要n大于5，保持划分n，低于5的数字贡献0 个 … 6贡献1个 因为6！= 6 x 5 x 4 x 3 x 2 x 1…以此类推）

综上，对n进行划分，每/5计算一次贡献，把这些贡献叠加起来就是最后产生的“尾0”的数量。
--------------------- 
作者：Mr.Bean-Pig 
来源：CSDN 
原文：https://blog.csdn.net/z714405489/article/details/88757960 
版权声明：本文为博主原创文章，转载请附上博文链接！

class Solution {
    public int trailingZeroes(int n) {
    	return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}