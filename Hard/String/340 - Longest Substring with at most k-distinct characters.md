# 题目描述
给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。

```java
示例 1:

输入: s = "eceba", k = 2
输出: 3
解释: 则 T 为 "ece"，所以长度为 3。

示例 2:
输入: s = "aa", k = 1
输出: 2
解释: 则 T 为 "aa"，所以长度为 2。

```

# 思路：滑动窗口
套模板：

```java
/* 滑动窗口算法框架 */
void slidingWindow(string s, string t) {
    HashMap<Character, Integer> need，window;

    for (int i = 0; i < t.length(); i++) {
    	// 初始化目标map
    }

    int left = 0, right = 0;
    int valid = 0; 

    while (right < s.length()) {
        // c 是将移入窗口的字符
        char c = s.charAt(right);
        // 右移窗口
        right++;
        // 进行窗口内数据的一系列更新(增加window这个map中的计数)
        ...

        /*** debug 输出的位置 ***/
        System.out.printf("window: %d, %d", left, right);
        /********************/

        // 判断左侧窗口是否要收缩
        while (window needs shrink) {
            // d 是将移出窗口的字符
            char d = s.charAt(left);
            // 左移窗口
            left++;
            // 进行窗口内数据的一系列更新(减少window这个map中的计数)
            ...
        }
    }
    // 返回结果
}
```
收缩条件：窗口内的字符种数 > k
结果更新的时机：每次窗口右移后

# 代码：

```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
    	HashMap<Character, Integer> window = new HashMap<Character, Integer>();
    	int i = 0, j = 0;
    	int ch_num = 0;
    	int res = 0;
    	int len = s.length();
    	while(j < len) {
    		// c 是将移入窗口的字符
        	char c = s.charAt(j);
    		// 窗口右移
    		j++;
    		// 更新数据
    		if (window.getOrDefault(c, 0) > 0) {
    			window.put(c, window.get(c) + 1);
    		} else {
    			// 新来的
    			ch_num++;
    			window.put(c, 1);
    		}

            // 更新结果
            if (j - i  > res && ch_num <= k) {
    			res = j - i ;
    		}

    		// 判断左窗口是否要收缩
    		while(ch_num > k) {

    			// d 是将移出窗口的字符
        		char d = s.charAt(i);
        		// 窗口左移
        		i++;
        		// 更新数据
        		window.put(d, window.get(d) - 1);
        		if (window.get(d) == 0) {
        			ch_num--;
        		}
    		}		
    	}
    	return res;
    }
}
```
时间复杂度：O（n）
空间复杂度：O（k）