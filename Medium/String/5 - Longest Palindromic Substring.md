# 题目描述
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

```java
示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：

输入: "cbbd"
输出: "bb"
```

# 思路1：暴力枚举
遍历所有长度大于2的子串，并逐一判断该子串是否为回文串。



时间复杂度：O(N^3^) ，N 是字符串的长度，枚举字符串的左边界、右边界，然后继续验证子串是否是回文子串，这三种操作都与 N 相关；
空间复杂度：O(1)

## 代码

```java
class Solution {
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 维护最长串的长度
        int maxLen = 1;
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        // 枚举所有长度大于 1 的子串 charArray[i..j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
            	// 验证
                if (j - i + 1 > maxLen && validPalindromic(charArray, i, j)) {
                	// 更新最长串的长度
                    maxLen = j - i + 1;
                    // 更新起点便于返回子串
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    /**
     * 验证子串 s[left..right] 是否为回文串
     */
    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

# 思路2：动态规划

这道题比较烦人的是判断回文子串。因此需要一种能够快速判断原字符串的所有子串是否是回文子串的方法，于是想到了「动态规划」。

「动态规划」的一个关键的步骤是想清楚「状态如何转移」。事实上，「回文」天然具有「状态转移」性质。

一个回文去掉两头以后，剩下的部分依然是回文（这里暂不讨论边界情况）；
依然从回文串的定义展开讨论：

如果一个字符串的头尾两个字符都不相等，那么这个字符串一定不是回文串；
如果一个字符串的头尾两个字符相等，才有必要继续判断下去。
如果里面的子串是回文，整体就是回文串；
如果里面的子串不是回文串，整体就不是回文串。

即：**在头尾字符相等的情况下，里面子串的回文性质据定了整个子串的回文性质，这就是状态转移。因此可以把「状态」定义为原字符串的一个子串是否为回文子串。**

## 第 1 步：定义状态
dp[i][j] 表示子串 s[i..j] 是否为回文子串，这里子串 s[i..j] 定义为左闭右闭区间，可以取到 s[i] 和 s[j]。

## 第 2 步：思考状态转移方程
在这一步分类讨论（根据头尾字符是否相等），根据上面的分析得到：


<font color=red>**dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]**

说明：

「动态规划」事实上是在填一张二维表格，由于构成子串，因此 i 和 j 的关系是 i <= j ，因此，**只需要填这张表格对角线以上的部分**。

看到 dp[i + 1][j - 1] 就得考虑边界情况。

**边界条件是：表达式 [i + 1, j - 1] 不构成区间，即长度严格小于 2，即 j - 1 - (i + 1) + 1 < 2 ，整理得 j - i < 3。**

这个结论很显然：j - i < 3 等价于 j - i + 1 < 4，**即当子串 s[i..j] 的长度等于 2 或者等于 3 的时候，其实只需要判断一下头尾两个字符是否相等就可以直接下结论了。**

如果子串 s[i + 1..j - 1] 只有 1 个字符，即去掉两头，剩下中间部分只有 11 个字符，显然是回文；
如果子串 s[i + 1..j - 1] 为空串，那么子串 s[i, j] 一定是回文子串。
因此，在 s[i] == s[j] 成立和 j - i < 3 的前提下，直接可以下结论，dp[i][j] = true，否则才执行状态转移。

## 第 3 步：考虑初始化
初始化的时候，单个字符一定是回文串，因此把对角线先初始化为 true，即 dp[i][i] = true 。

事实上，初始化的部分都可以省去。因为只有一个字符的时候一定是回文，**dp[i][i] 根本不会被其它状态值所参考**。

## 第 4 步：考虑输出
只要一得到 dp[i][j] = true，就记录子串的长度和起始位置，没有必要截取，这是因为截取字符串也要消耗性能，记录此时的回文子串的「起始位置」和「回文长度」即可。

## 第 5 步：考虑优化空间
因为在填表的过程中，只参考了左下方的数值。事实上可以优化，但是增加了代码编写和理解的难度，丢失可读和可解释性。在这里不优化空间。

注意事项：总是先得到小子串的回文判定，然后大子串才能参考小子串的判断结果，即填表顺序很重要。

> 作者：liweiwei1419
> 链接：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
> 来源：力扣（LeetCode）
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

下面自己画了一下例子：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201108154838209.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70#pic_center)

注意到红圈的位置，由于子串长度小于4，可以通过判断首尾直接确定这个格子的值，剩余的格子则需要对左下参考(如果首尾不一致则直接判否无需参考)。

填表顺序有多种，但一定要注意左下格子的值要先于右上的格子计算出来，因为左下的格子是被参考值。

时间复杂度：O(N^2^) ，相比于暴力法，动态规划的填表法省去了判断子串是否为回文这个步骤，因此复杂度下降了一次幂。
空间复杂度：O(N^2^)，二维数组所花费的空间。

## 代码

```java
class Solution {
   public String longestPalindrome(String s) {
        // 特判
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        // // 将对角线初始化（没必要）
        // for (int i = 0; i < len; i++) {
        //     dp[i][i] = true;
        // }

        // 计算dp[i][j]，由状态转移方程可知，需要参考左下角dp[i + 1][j - 1]
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
            	// 首先判断首尾字符
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                	// 子串长度小于4无需判断
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                    	// 否则参考子串（左下表格）的值
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        // 根据记录的（最长）位置返回子串
        return s.substring(begin, begin + maxLen);
    }
}
```
# 思路3：中心扩散
因此中心扩散法的思路是：遍历每一个索引，以这个索引为中心，利用“回文串”中心对称的特点，往两边扩散，看最多能扩散多远。

枚举“中心位置”时间复杂度为 O(N)，从“中心位置”扩散得到“回文子串”的时间复杂度为 O(N)，因此时间复杂度可以降到 O(N^2^)。

在这里要注意一个细节：回文串在长度为奇数和偶数的时候，“回文中心”的形式是不一样的。

- 奇数回文串的“中心”是一个具体的字符，例如：回文串 "aba" 的中心是字符 "b"；
- 偶数回文串的“中心”是位于中间的两个字符的“空隙”，例如：回文串串 "abba" 的中心是两个 "b" 中间的那个“空隙”。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201108160834266.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70#pic_center)
我们可以设计一个方法，兼容以上两种情况：

<font color=red> 1、如果传入重合的索引编码，进行中心扩散，此时得到的回文子串的长度是奇数；
2、如果传入相邻的索引编码，进行中心扩散，此时得到的回文子串的长度是偶数。</font>

## 代码

```java
// 中心扩散
public class Solution {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);
        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < len - 1; i++) {
        	// 长度为奇数的回文串（回文中心是一个字符）
            String oddStr = centerSpread(s, i, i);
            // 长度为偶数数的回文串（回文中心是一个缝隙）
            String evenStr = centerSpread(s, i, i + 1);
            // 两种扩散方式取较大值
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            // 更新最长子串的长度与结果
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }
        return res;
    }

    // 尽力而为，返回能扩散到的最远的子串
    private String centerSpread(String s, int left, int right) {
        // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
        int len = s.length();
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
        	// 首尾相同则扩散
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // 这里要小心，跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，因此不能取 i，不能取 j
        return s.substring(i + 1, j);
    }
}
```

> 作者：liweiwei1419
> 链接：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
> 来源：力扣（LeetCode）
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。