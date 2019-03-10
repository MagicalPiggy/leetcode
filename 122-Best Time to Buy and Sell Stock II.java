// Say you have an array for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

// Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

// Example 1:

// Input: [7,1,5,3,6,4]
// Output: 7
// Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
//              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.

// Example 2:

// Input: [1,2,3,4,5]
// Output: 4
// Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
//              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
//              engaging multiple transactions at the same time. You must sell before buying again.

// Example 3:

// Input: [7,6,4,3,1]
// Output: 0
// Explanation: In this case, no transaction is done, i.e. max profit = 0.

// [1,5,3,6,4,8]  11
// [1,3,5,4,2,7]   9

// 思路
// “炒股”问题，要想利润最大化，那么就得低买高卖，赚取利润，如何判断有利润？只有当这一天的价格大于前一天的价格时才会有利润。可以遍历一趟整个数组，统计所有交易的利润，也就是把所有的正差价加起来。只要后一天的数字大于前一天，那么就把这两天之间的“利润”算进去。
// 因为所有的“涨势”，例如[1,2,3,4]，看起来只做了一组交易（1买4卖，profit=4-1=3），但其实每一天都在产生利润（profit= (2-1) + (3-2) + (4-3) =3）
// 时间复杂度 O(n)


class Solution {
    public int maxProfit(int[] prices) {
    	int profit = 0;
        for(int i = 0 ; i < prices.length-1 ; i ++){
        	if(prices[i] < prices[i+1]) 
        		profit+=prices[i+1]-prices[i];

        }
        return profit;
    }
}