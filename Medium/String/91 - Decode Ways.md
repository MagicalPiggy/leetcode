# 题目描述

一条包含字母 A-Z 的消息通过以下方式进行了编码：

'A' -> 1
'B' -> 2
...
'Z' -> 26
给定一个只包含数字的非空字符串，请计算解码方法的总数。

题目数据保证答案肯定是一个 32 位的整数。



```java
示例1:

输入：s = "12"
输出：2
解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 

示例2:

输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 

示例3:
输入：s = "0"
输出：0

实例4：
输入：s = "1"
输出：1

示例5：
输入：s = "2"
输出：1

提示：

1 <= s.length <= 100
s 只包含数字，并且可能包含前导零。
```



每个数字对应一个字母，给一串数字，问有几种解码方式。例如 226 可以有三种，2|2|6，22|6，2|26。

# 解法一 递归
很容易想到递归去解决，将大问题化作小问题。

比如 232232323232。

对于第一个字母我们有两种划分方式。

2|32232323232 和 23|2232323232

所以，如果我们分别知道了上边划分的右半部分 32232323232 的解码方式是 ans1 种，2232323232 的解码方式是 ans2 种，那么整体 232232323232 的解码方式就是 ans1 + ans2 种。可能一下子，有些反应不过来，可以看一下下边的类比。

假如从深圳到北京可以经过武汉和上海两条路，而从武汉到北京有 8 条路，从上海到北京有 6 条路。那么从深圳到北京就有 8 + 6 = 14 条路。


```java
public int numDecodings(String s) {
    return getAns(s, 0);
}

private int getAns(String s, int start) {
    //划分到了最后返回 1
    if (start == s.length()) {
        return 1;
    }
    //开头是 0,0 不对应任何字母，直接返回 0
    if (s.charAt(start) == '0') {
        return 0;
    }
    //得到第一种的划分的解码方式
    int ans1 = getAns(s, start + 1);
    int ans2 = 0;
    //判断前两个数字是不是小于等于 26 的
    if (start < s.length() - 1) {
        int ten = (s.charAt(start) - '0') * 10;
        int one = s.charAt(start + 1) - '0';
        if (ten + one <= 26) {
            ans2 = getAns(s, start + 2);
        }
    }
    return ans1 + ans2;
}
```


# 解法二 递归 memoization
解法一的递归中，走完左子树，再走右子树会把一些已经算过的结果重新算，所以我们可以用 memoization 技术，就是算出一个结果很就保存，第二次算这个的时候直接拿出来就可以了。


```java
public int numDecodings(String s) {
    HashMap<Integer, Integer> memoization = new HashMap<>();
    return getAns(s, 0, memoization);
}

private int getAns(String s, int start, HashMap<Integer, Integer> memoization) {
    if (start == s.length()) {
        return 1;
    }
    if (s.charAt(start) == '0') {
        return 0;
    }
    //判断之前是否计算过
    int m = memoization.getOrDefault(start, -1);
    if (m != -1) {
        return m;
    }
    int ans1 = getAns(s, start + 1, memoization);
    int ans2 = 0;
    if (start < s.length() - 1) {
        int ten = (s.charAt(start) - '0') * 10;
        int one = s.charAt(start + 1) - '0';
        if (ten + one <= 26) {
            ans2 = getAns(s, start + 2, memoization);
        }
    }
    //将结果保存
    memoization.put(start, ans1 + ans2);
    return ans1 + ans2;
}
```

# 解法三 动态规划
同样的，递归就是压栈压栈压栈，出栈出栈出栈的过程，我们可以利用动态规划的思想，省略压栈的过程，直接从 bottom 到 top。

用一个 dp 数组， dp [ i ] 代表字符串 s [ i, s.len-1 ]，也就是 s 从 i 开始到结尾的字符串的解码方式。

这样和递归完全一样的递推式。

如果 s [ i ] 和 s [ i + 1 ] 组成的数字小于等于 26，那么

**dp [ i ] = dp[ i + 1 ] + dp [ i + 2 ]**


```java
public int numDecodings(String s) {
    int len = s.length();
    int[] dp = new int[len + 1];
    dp[len] = 1; //将递归法的结束条件初始化为 1 
    //最后一个数字不等于 0 就初始化为 1
    if (s.charAt(len - 1) != '0') {
        dp[len - 1] = 1;
    }
    for (int i = len - 2; i >= 0; i--) {
        //当前数字时 0 ，直接跳过，0 不代表任何字母
        if (s.charAt(i) == '0') {
            continue;
        }
        int ans1 = dp[i + 1];
        //判断两个字母组成的数字是否小于等于 26
        int ans2 = 0;
        int ten = (s.charAt(i) - '0') * 10;
        int one = s.charAt(i + 1) - '0';
        if (ten + one <= 26) {
            ans2 = dp[i + 2];
        }
        dp[i] = ans1 + ans2;

    }
    return dp[0];
}
```

接下来就是，动态规划的空间优化了，例如5题，10题，53题，72题等等都是同样的思路。都是注意到一个特点，当更新到 dp [ i ] 的时候，我们只用到 dp [ i + 1] 和 dp [ i + 2]，之后的数据就没有用了。所以我们不需要 dp 开 len + 1 的空间。

简单的做法，我们只申请 3 个空间，然后把 dp 的下标对 3 求余就够了。


```java
public int numDecodings4(String s) {
    int len = s.length();
    int[] dp = new int[3];
    dp[len % 3] = 1;
    if (s.charAt(len - 1) != '0') {
        dp[(len - 1) % 3] = 1;
    }
    for (int i = len - 2; i >= 0; i--) {
        if (s.charAt(i) == '0') {
            dp[i % 3] = 0; //这里很重要，因为空间复用了，不要忘记归零
            continue;
        }
        int ans1 = dp[(i + 1) % 3];
        int ans2 = 0;
        int ten = (s.charAt(i) - '0') * 10;
        int one = s.charAt(i + 1) - '0';
        if (ten + one <= 26) {
            ans2 = dp[(i + 2) % 3];
        }
        dp[i % 3] = ans1 + ans2;

    }
    return dp[0];
}
```

然后，如果多考虑以下，我们其实并不需要 3 个空间，我们只需要 2 个就够了，只需要更新的时候，指针移动一下，代码如下。


```java
public int numDecodings5(String s) {
    int len = s.length();
    int end = 1;
    int cur = 0;
    if (s.charAt(len - 1) != '0') {
        cur = 1;
    }
    for (int i = len - 2; i >= 0; i--) {
        if (s.charAt(i) == '0') {
            end = cur;//end 前移
            cur = 0;
            continue;
        }
        int ans1 = cur;
        int ans2 = 0;
        int ten = (s.charAt(i) - '0') * 10;
        int one = s.charAt(i + 1) - '0';
        if (ten + one <= 26) {
            ans2 = end;
        }
        end = cur; //end 前移
        cur = ans1 + ans2;

    }
    return cur;
}
```



作者：windliang
链接：https://leetcode-cn.com/problems/decode-ways/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-3/