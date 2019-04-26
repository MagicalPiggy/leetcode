Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

// 先建立一个布尔数组notPrime，用来标记n范围内的每一个数是否为质数，若为质数，标记应该为false，非质数则标为true。
// 初始状态下，所有标记都为false。
// 外层循环：i从2开始遍历至n，若发现质数（标记为false），则计数++。
// 内层循环：将外层循环的i所有倍数都置为true。
// 这样循环结束后所有的非质数都被标记为true。所有的质数都会被统计。

public class Solution {
    public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];//初始为false
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {//false表示该数为质数
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;//将i的所有倍数置为true，也就是非质数
                }
            }
        }
        
        return count;
    }
}


//将上面的代码进行改进可以进一步提升效率。
// 因为在n范围内，只需要检测所有的奇数即可，因为所有的偶数（2除外）都不可能为质数。
// 从结果可以看到，运行速度快了不少。
public class Solution {
    public int countPrimes(int n) {
    	if (n < 3)
        	return 0;
        
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 3; i < n; i += 2) {//遍历所有的奇数
            if (notPrime[i] == false) {
                count++;
                for (int j = 3; i*j < n; j+=2) {//对其倍数（的标记）操作也只是操作所有的奇数
                    notPrime[i*j] = true;
                }
            }
        }
        
        return count+1;//没有把2算进去，所有+1
    }
}

