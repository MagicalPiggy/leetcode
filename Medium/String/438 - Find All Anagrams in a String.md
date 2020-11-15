# 题目描述
给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。

```java
示例 1:
输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。 示例 2:

输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]

解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
```

# 思路：滑动窗口
这题与 **leetcode-567 Permutation in String（字符串的排列）**几乎一致，只是输出略有不同而已。

## 代码

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
    	List<Integer> result = new ArrayList<Integer>();
    	// 特判
    	if (s == null || s.length() < p.length()) {
    		return result;
    	}
    	HashMap<Character,Integer> source = new HashMap<Character,Integer>();
    	HashMap<Character,Integer> target = new HashMap<Character,Integer>();
    	// 初始化
    	for (int i = 0; i < p.length(); i++) {
    		target.put(p.charAt(i), target.getOrDefault(p.charAt(i), 0) + 1);
    	}
    	int left = 0, right = 0;
    	int valid = 0;
    	while (right < s.length()) {
    		char c = s.charAt(right);
    		// 窗口右移
    		right++;
    		// 更新窗口内的值
    		if (target.containsKey(c)) {
    			source.put(c, target.getOrDefault(c, 0) + 1);
    			if (source.get(c).equals(target.get(c))) {
    				valid++;
    			}
    		}

    		while (right - left >= p.length()) {
    			// 更新结果
    			if (valid == target.size()) {
    				result.add(left);
    			}
    			char d = s.charAt(left);
    			// 窗口左移
    			left++;
    			// 更新窗口内的值
    			if (target.containsKey(d)) {
    				if (source.get(d).equals(target.get(d))) {
    					valid--;
    				}
    				source.put(d, target.getOrDefault(d, 0) - 1);
    			}
    		}
    	}
    	return result;
    }
}
```
时间复杂度O(N)：其中 N = s.length()
空间复杂度O(1)：常数空间