# 题目描述
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素
所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。


```
示例 1：
输入：s = "aa" p = "a"
输出：false
解释："a" 无法匹配 "aa" 整个字符串。

示例 2:
输入：s = "aa" p = "a*"
输出：true
解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

示例 3：
输入：s = "ab" p = ".*"
输出：true
解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

示例 4：
输入：s = "aab" p = "c*a*b"
输出：true
解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。

示例 5：
输入：s = "mississippi" p = "mis*is*p*."
输出：false
```
# 简单分析
这个正则表达式中主要出现了两种符号。

【点号】通配符其实很好实现，
s中的任何字符，只要遇到 . 通配符，无脑匹配就完事了。

主要是这个【星号】通配符不好实现，一旦遇到 * 通配符，
<font color=red> 前面的那个字符可以选择重复一次，可以重复多次，也可以一次都不出现</font>，
这该怎么办？

对于这个问题，答案很简单，对于所有可能出现的情况，全部穷举一遍，只要有一种情况可以完成匹配，就认为
p可以匹配s。

那么一旦涉及两个字符串的穷举，我们就应该条件反射地想到<font color=red> 动态规划 </font>的技巧。
 # 思路：动态规划



s 和 p 相互匹配的过程大致是，两个指针 i ,  j 分别在 s 和 p 上移动，如果最后**两个指针都能移动到字符串的末尾**，那么就匹配成功，反之则匹配失败。

正则表达算法问题只需要把住一个中心：
看两个字符是否匹配，一切逻辑围绕【匹配/不匹配】两种情况展开即可。

## 不考虑【*】
若只考虑【.】通配符，当面对两个待匹配的字符，直接判断即可：

```java
boolean isMatch(String s, String p) {
    int i = 0, j = 0;
    while (i < s.length() && j < p.length()) {
        // 「.」通配符就是万金油
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            // 匹配，接着匹配后面一位的字符
            i++; j++;
        } else {
            // 不匹配
            return false;
        }
    }
    return i == j;
}
```
## 考虑【*】
稍微复杂，分情况来分析。
因为星号总是会出现在一个字符的后面，所以我们考虑 j+1 这个位置上为【*】的情况。

1、如果当前字符匹配，即s.charAt(i) == p.charAt(j)，那么有两种情况：
- 1.1
p.charAt(j) 有可能会匹配**多个**字符，比如s = "**aaa**", p = **"a\***"，那么p[0]会通过 * 匹配 3 个字符"a"。

- 1.2
p.charAt(j) 也有可能匹配 0 个字符，比如s = "**aa**", p = "**a\*aa**"，由于后面的字符"..aa"可以匹配s，所以**a***只能匹配 0 次。

2、如果当前字符不匹配，即s.charAt(i) != p.charAt(j)，只有一种情况，能让匹配继续进行：
p.charAt(j)和后面的 * 只能匹配 0 次，此时只能观察下一个字符是否能和s[i]匹配。
比如说s = "**aa**", p = **"b*aa**"，此时b\*只能匹配 0 次。

当然，在没有通配符的情况下，此处出现不匹配则宣告失败。


综上，可以把之前的代码针对 * 通配符进行改造：

```java
boolean isMatch(String s, String p) {
    int i = 0, j = 0;
    while (i < s.length() && j < p.length()) {
        // 1 匹配
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
        	// 1.1 1.2 后面有 * 通配符，可以匹配 0 次或多次
		    if (j < p.size() - 1 && p[j + 1] == '*') {
		        ...
		    } else {
		        // 无 * 通配符，老老实实匹配 1 次
		        i++; j++;
		    }
        } else {
            // 2 不匹配
            // 有 * 通配符，只能匹配 0 次
		    if (j < p.size() - 1 && p[j + 1] == '*') {
		        ...
		    } else {
		        // 无 * 通配符，匹配无法进行下去了
		      	return false;
		    }
        }
    }
    return i == j;
}
```

整体的思路已经很清晰了，但现在的问题是，遇到 * 通配符时，到底应该匹配 0 次还是匹配多次？多次是几次？

这就是一个做「选择」的问题，要把所有可能的选择都穷举一遍才能得出结果。动态规划算法的核心就是「状态」和「选择」，<font color=red> 「状态」就是 i 和 j 两个指针的位置，「选择」就是 p[j] 选择匹配几个字符。 </font>
## 解法
根据「状态」，我们可以定义一个 dp 函数：

```java
boolean dp(String s, int i, String p, int j)
```



函数的定义如下：

若
dp(s,i,p,j) = true，则表示s[i..]可以匹配p[j..]；若
dp(s,i,p,j) = false，则表示s[i..]无法匹配p[j..]。

根据这个定义，我们想要的答案就是i = 0,j = 0时dp函数的结果，所以可以这样使用这个dp函数：

```java
boolean isMatch(String s, String p) {
    // 指针 i，j 从索引 0 开始移动
    return dp(s, 0, p, 0);
}
```


可以根据之前的代码写出 dp 函数的主要逻辑，整体采用递归的形式将指针往后移动：

```java
 /* 计算 p[j..] 是否匹配 s[i..] */
	boolean dp(String s, int i, String p, int j) {
	// 1 匹配
	    if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {	    	
	        if (j < p_size - 1 && p.charAt(j+1) == '*') {
	        	// 1.1 有通配符，通配符匹配 0 次或多次
	           return dp(s, i, p, j + 2)
	               || dp(s, i + 1, p, j);
	        } else {
	        	// 1.2 无通配符，常规匹配 1 次
	           return dp(s, i + 1, p, j + 1);
	        }
	    } else {
	    	// 2 不匹配
	        if (j < p_size - 1 && p.charAt(j+1) == '*') {
	        	// 2.1 有通配符，通配符匹配 0 次
	            return dp(s, i, p, j + 2);
	        } else {
	        	// 2.2 无通配符，无法继续匹配
	            return false;
	        }
	    }
	   }
```
解释几种情况：
- 1.1 <font color=red>通配符匹配 0 次或多次 </font>
dp(s, i, p, j + 2)，含义就是直接跳过p[j]和p[j]的*，考虑后面的字符：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201119151202707.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70#pic_center)
dp(s, i + 1, p, j),就是当前字符和*还可以继续匹配，即通配符匹配多次：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201119151316310.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70#pic_center)
两种情况只要有一种可以完成匹配即可，所以对上面两种情况求或运算。

- 1.2 <font color=red>常规匹配 1 次</font>
dp(s, i + 1, p, j + 1)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201119151506607.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70#pic_center)

- 2.1 <font color=red>通配符匹配 0 次</font>
dp(s, i, p, j + 2)
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020111915185783.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70#pic_center)

- 2.2 <font color=red>匹配失败</font>
当前字符不匹配且后面没有通配符：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201119151919766.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70#pic_center)
## base case
结束条件，一个是模式串匹配到了末尾，一个是文本串匹配到了末尾。

- j == p.length()

模式串匹配到了末尾，那么应该看看文本串s匹配到哪里了，如果s也恰好被匹配完，则说明匹配成功：
```java
if (j == p.length()) {
    return i == s.length();
}
```
- i == s.length()
文本串已经全部被匹配了，那么是不是只要简单地检查一下p是否匹配完就行了？
这是不正确的，此时并不能根据j是否等于p.length()来判断是否完成匹配，**只要p[j..]能够匹配空串，就可以算完成匹配**。
比如说s = "**a**", p = "**ab\*c***"，当i走到s末尾的时候，j并没有走到p的末尾，但是p依然可以匹配s。
因此，这种情况应该这么判断：

```java
// base case 2:文本串被匹配完了
   if (i == s_size) {
    	// 如果能匹配空串，一定是字符和 * 成对儿出现
        if ((p_size - j) % 2 == 1) {
            return false;
        }
        // 检查是否为 x*y*z* 这种形式
        for (; j + 1 < p_size; j += 2) {
            if (p.charAt(j+1) != '*') {
                return false;
            }
        }
        return true;
    }
```
## 消除重叠子问题
由上可提炼出，正则表达算法的递归框架如下：

```java
boolean dp(String s, int i, String p, int j) {
    dp(s, i, p, j + 2);     // 1
    dp(s, i + 1, p, j);     // 2
    dp(s, i + 1, p, j + 1); // 3
}
```
也就是说，通往最后“匹配”这个结果，需要经过不同的中间状态，且可以通过三种路径来实现。
如果让你从dp(s, i, p, j)得到dp(s, i+2, p, j+2)这个状态，至少有两条路径：

```java
1 -> 2 -> 2
和
3 -> 3
```
那么就说明(i+2, j+2)这个状态存在重复，这就说明存在重叠子问题。

所以代码中可以使用哈希表来记录转态，每次计算出结果就能保存到表中，下次还需计算，就可以直接取，如此就可以消除重叠子问题。


## 完整代码

```java
class Solution {
	HashMap<String, Boolean> memo = new HashMap<String, Boolean>();
    public boolean isMatch(String s, String p) {
    	return dp(s, 0, p, 0);
    }

    /* 计算 p[j..] 是否匹配 s[i..] */
	boolean dp(String s, int i, String p, int j) {
	    int s_size = s.length(), p_size = p.length();
	    // base case 1:模式串被匹配完了
	    if (j == p_size) {
	        return i == s_size;
	    }
	    // base case 2:文本串被匹配完了
	    if (i == s_size) {
	    	// 如果能匹配空串，一定是字符和 * 成对儿出现
	        if ((p_size - j) % 2 == 1) {
	            return false;
	        }
	        // 检查是否为 x*y*z* 这种形式
	        for (; j + 1 < p_size; j += 2) {
	            if (p.charAt(j+1) != '*') {
	                return false;
	            }
	        }
	        return true;
	    }

	    // 记录状态 (i, j)，消除重叠子问题
	    String key = i + "," + j;
	    if (memo.containsKey(key)) return memo.get(key);

	    boolean res = false;
	    // 1 匹配
	    if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {	    	
	        if (j < p_size - 1 && p.charAt(j+1) == '*') {
	        	// 1.1 有通配符，通配符匹配 0 次或多次
	            res = dp(s, i, p, j + 2)
	               || dp(s, i + 1, p, j);
	        } else {
	        	// 1.2 无通配符，常规匹配 1 次
	            res = dp(s, i + 1, p, j + 1);
	        }
	    } else {
	    	// 2 不匹配
	        if (j < p_size - 1 && p.charAt(j+1) == '*') {
	        	// 2.1 有通配符，通配符匹配 0 次
	            res = dp(s, i, p, j + 2);
	        } else {
	        	// 2.2 无通配符，无法继续匹配
	            res = false;
	        }
	    }
	    // 将当前结果记入备忘录
	    memo.put(key,res);

	    return res;
	}
}
```
时间复杂度：O(MN)
空间复杂度：O(MN)
M和N分别为两个子串的长度。