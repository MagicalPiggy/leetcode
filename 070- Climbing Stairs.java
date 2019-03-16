You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps


Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

//自下而上递归
class Solution {
    public int climbStairs(int n) {
    	if(n == 1)
    		return 1;
    	if(n == 2)
    		return 2;

        int res = climbStairs(n-1)+climbStairs(n-2);

        return res;
    }
}

//自上而下备忘录式(map)
class Solution {
    public int climbStairs(int n) {
    	if(n == 1)
    		return 1;
    	if(n == 2)
    		return 2;
    	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    	map.put(1,1);
    	map.put(2,2);

    	for(int i = 3;i < n ; i++){
    		map.put(i,map.get(i-1)+map.get(i-2));
    	}
    	return map.get(n-1)+map.get(n-2);

    }
}

//自上而下备忘录式(数组)
class Solution {
    public int climbStairs(int n) {
    	int memo = new int[n+1];
    	return climb_Stairs(0,n,memo[])
    }

    public int climb_Stairs(int i , int n, int memo[]){
    	if(i > n){
    		return 0;
    	}
    	if(i == n){
    		return 1;

    	}
    	if(memo[i] > 0)
    		return memo[i];
    	memo[i] = 
    }
}

//动态规划
class Solution {
public int climbStairs(int n) {
    // base cases
    if(n <= 0) return 0;
    if(n == 1) return 1;
    if(n == 2) return 2;
    

    int pre1 = 2;//当前阶的前1阶的走法数（当前阶从n=3开始算）
    int pre2 = 1;//当前阶的前2阶的走法数
    int all_ways = 0;
    
    for(int i=2; i<n; i++){
    	all_ways = pre1 + pre2;//当前阶的走法==前1阶+前2阶

    	//以下为之后的计算做准备，也就是状态转移
    	//当计算下1阶的走法时，该问题的前2阶就是本次循环中的前1阶
    	//而该问题的前1阶就是本次循环中的当前阶
    	pre2 = pre1;
        pre1 = all_ways;
    }
    return all_ways;
 }
}