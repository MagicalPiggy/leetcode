Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1


// class Solution {
//     public boolean isHappy(int n) {
//         while(n/10!=0){
//         	// n = Math.pow(n/100,2)  + Math.pow(n%100/10,2) + Math.pow(n%10,2) ;
//         	// int a = n/100;
//         	// int b = n%100/10;
//         	// int c = n%10;
//         	// n = a + b + c;
//         	while


//         }
//         if(n == 1 || n == 7)
//         	return true;
//         else
//         	return false;
//     }
// }


class Solution{
	public boolean isHappy(int n) {
	    Set<Integer> inLoop = new HashSet<Integer>();//用来保存所有运算产生的结果，这里面的结果都是不重复的
	    int squareSum,remain;
		while (inLoop.add(n)) {//add返回为true则继续循环语句，因为还未进入“循环”状态
			squareSum = 0;//用来保存平方后的和
			while (n > 0) {
			    remain = n%10;//取个位
				squareSum += remain*remain;//平方后加入结果
				n /= 10;//右移一位
			}
			if (squareSum == 1)
				return true;//happy ending ，直接返回
			else
				n = squareSum;//替换掉原来的数字继续下一次求和

		}
		return false;//退出while语句说明进入了循环

	}
}


class Solution{
	public boolean isHappy(int n) {
	if(n<10) {//个位数则判断
		if(n==1||n==7) return true;
		else return false;
	 }
	int b;int sum=0;
	while(n>0) {
		  b=n%10;
		  sum=sum+b*b; 
		  n=n/10;
	  } 
     return isHappy(sum);//递归求平方和
  }
}

class Solution{
	public boolean isHappy(int n) {
	   
	    int squareSum,remain;
		while (n>=10) {//add返回为true则继续循环语句，因为还未进入“循环”状态
			squareSum = 0;//用来保存平方后的和
			while (n > 0) {
			    remain = n%10;//取个位
				squareSum += remain*remain;//平方后加入结果
				n /= 10;//右移一位
			}
			// if (squareSum == 1)
			// 	return true;//happy ending ，直接返回
			// else
				n = squareSum;//替换掉原来的数字继续下一次求和

		}
		if(n == 1 || n ==7)
			return true;
		else 
			return false;//退出while语句说明进入了循环

	}
}
