# 题目描述
给定一个字符串 s 和一个字符模式 p，实现一个支持 '?' 和 '*' 的通配符匹配。

'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。

说明:

s 可能为空，且只包含从 a-z 的小写字母。
p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。

```csharp
示例 1:
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。

示例 2:
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。

示例 3:
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。

示例 4:
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".

示例 5:
输入:
s = "acdcb"
p = "a*c?b"
输出: false
```

# 思路：动态规划
这题比 【leetcode-10 正则表达式匹配】要稍简单些，因为在本题中，模式 p 中的任意一个字符都是独立的，即不会和前后的字符互相关联，形成一个新的匹配模式。

回顾 leetcode-10的框架：

```java
boolean dp(String s, int i, String p, int j) {
    dp(s, i, p, j + 2);     // 1
    dp(s, i + 1, p, j);     // 2
    dp(s, i + 1, p, j + 1); // 3
}
```
本题的状态有以下几类：

```java
1.匹配
	1.1 不匹配*，匹配后一位
	1.2 匹配*，匹配几位？
	1.3 匹配单个字符
2.不匹配
```
那么套用框架可以写出代码：

```java
class Solution {
	HashMap<String, Boolean> memo = new HashMap<String, Boolean>();
    public boolean isMatch(String s, String p) {
    	return dp(s, 0, p, 0);
    }

    private boolean dp(String s, int i, String p, int j) {
    	// base case
    	// 模式串被匹配完了
    	if (j == p.length()) {
    		return i == s.length();
    	}

    	// 文本串被匹配完了
    	if (i == s.length()) {
    		while (j < p.length() && p.charAt(j) == '*' ) {
    			j++;
    		}
    		return j == p.length();
    	}

	    // 记录状态 (i, j)，消除重叠子问题
	    String key = i + "," + j;
	    if (memo.containsKey(key)) return memo.get(key);

	    boolean res = false;

    	// 匹配,无*
    	if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
    		res = dp(s, i + 1, p, j + 1);
    	}
    	// 匹配,有*
    	else if (p.charAt(j) == '*') {
    		res = dp(s, i, p, j + 1) || dp(s, i + 1, p, j);
    	}
    	else {
    		res = false;
    	}

    	// 将当前结果记入备忘录
	    memo.put(key,res);
	    return res;
    }
}
```
# 另一种写法

```java
class Solution {
    // 状态 dp[i][j] : 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配 (true 的话表示匹配)
    // 状态转移方程：
    //      1. 当 s[i] == p[j]，或者 p[j] == ? 那么 dp[i][j] = dp[i - 1][j - 1];
    //      2. 当 p[j] == * 那么 dp[i][j] = dp[i][j - 1] || dp[i - 1][j]    其中：
    //      dp[i][j - 1] 表示 * 代表的是空字符，例如 ab, ab*
    //      dp[i - 1][j] 表示 * 代表的是非空字符，例如 abcd, ab*
    // 初始化：
    //      1. dp[0][0] 表示什么都没有，其值为 true
    //      2. 第一行 dp[0][j]，换句话说，s 为空，与 p 匹配，所以只要 p 开始为 * 才为 true
    //      3. 第一列 dp[i][0]，当然全部为 false (数组默认都是false所以无需操作)
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // 状态 dp[i][j] : 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 初始化
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        
        // 返回结果
        return dp[m][n];

    }
}


```

> 作者：tangweiqun
> 链接：https://leetcode-cn.com/problems/wildcard-matching/solution/dong-tai-gui-hua-dai-zhu-shi-by-tangweiqun/
> 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

以s = abcd 和 p = "ab*" 为例填一遍表格可以理解p.charAt(j - 1) = '*'的推导情况。