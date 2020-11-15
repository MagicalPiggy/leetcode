# 题目描述
给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

```java
示例1:

输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").
 

示例2:

输入: s1= "ab" s2 = "eidboaoo"
输出: False
 

注意：

输入的字符串只包含小写字母
两个字符串的长度都在 [1, 10,000] 之间
```

# 思路：滑动窗口
首先需要脑筋急转弯，所谓“排列”，就是要求s2中对应元素连续，但可以顺序不同。
也就是说，只要包含s1的几个字符(小写字母)，【频数】相同即可，不要求字母顺序一致。
这里直接上滑动窗口模板~

## 代码

```java
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // 特判
    	if (s1 == null || s2 == null || s1.length() > s2.length())
        return false;

    	HashMap<Character,Integer> source = new HashMap<Character,Integer>();
    	HashMap<Character,Integer> target = new HashMap<Character,Integer>();
    	// 初始化target的哈希表
    	for (int i = 0; i < s1.length(); i++) {
    		target.put(s1.charAt(i), target.getOrDefault(s1.charAt(i), 0) + 1);
    	}

    	int left = 0, right = 0;
    	int valid = 0;
    	while(right < s2.length()) {
    		char c = s2.charAt(right);
    		// 窗口右移
    		right++;
    		// 更新窗口内数据
    		if (target.containsKey(c)) {
    			source.put(c, source.getOrDefault(c, 0) + 1);
    			if (source.get(c).equals(target.get(c))) {
    				valid++;
    			}
    		}

    		// 判断左侧窗口是否需要收缩
    		while(right - left >= s1.length()) {
    			if (valid == target.size()) {
    				return true;
    			}
    			char d = s2.charAt(left);
    			// 左移窗口
    			left++;
    			// 更新窗口内数据
    			if (target.containsKey(d)) {
    				if (source.get(d).equals(target.get(d))) {
    					valid--;
    				}
    				source.put(d, source.getOrDefault(d, 0) - 1);
    			}
    		}
    	}
    	return false;
    }
}
```
时间复杂度O(N)：其中 N = s2.length()
空间复杂度O(1)：常数空间