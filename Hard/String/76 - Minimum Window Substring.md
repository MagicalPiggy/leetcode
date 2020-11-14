# 题目描述

给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。



```java
示例 1：
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"

示例 2：
输入：s = "a", t = "a"
输出："a"
 

提示：
1 <= s.length, t.length <= 105
s 和 t 由英文字母组成
```
# 思路：滑动窗口
如果我们使用暴力解法，代码大概是这样的：

```java
for (int i = 0; i < s.size(); i++)
    for (int j = i + 1; j < s.size(); j++)
        if s[i:j] 包含 t 的所有字母:
            更新答案
```

思路很直接，但是显然，这个算法的复杂度肯定大于 O(N^2) 了，不好。
与leetcode-5相似，此题可用**滑动窗口**算法解决，从而实现O(N) 的时间复杂度。


滑动窗口算法的思路是这样：

1、在字符串S中使用双指针中的左右指针技巧，初始化left = right = 0，把索引左闭右开区间[left, right)称为一个「窗口」。

2、先不断地增加right指针<font color=red>扩大窗口</font>[left, right)，直到窗口中的字符串符合要求（包含了T中的所有字符）。

3、此时，停止增加right，转而不断增加left指针<font color=red>缩小窗口</font>[left, right)，直到窗口中的字符串不再符合要求（不包含T中的所有字符了）。同时，每次增加left，都要更新一轮结果。

4、重复第 2 和第 3 步，直到right到达字符串S的尽头。

这个思路其实也不难，第 2 步相当于在寻找一个「可行解」，然后第 3 步在优化这个「可行解」，最终找到最优解，也就是最短的覆盖子串。左右指针轮流前进，窗口大小增增减减，窗口不断向右滑动，这就是「滑动窗口」这个名字的来历。


## 滑动窗口算法框架

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
## 本题中的滑动窗口
首先，初始化window和need两个哈希表，记录**窗口中的字符**和**需要凑齐的字符**；
然后，使用left和right变量初始化窗口的两端，不要忘了，区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素；
其中valid变量表示窗口中满足need条件的字符个数，如果valid和need.size的大小相同，则说明窗口已满足条件，已经完全覆盖了串T。

现在开始套模板，只需要思考以下四个问题：

**1、当移动right扩大窗口，即加入字符时，应该更新哪些数据？
2、什么条件下，窗口应该暂停扩大，开始移动left缩小窗口？
3、当移动left缩小窗口，即移出字符时，应该更新哪些数据？
4、我们要的结果应该在扩大窗口时还是缩小窗口时进行更新？**

如果一个字符进入窗口，应该增加window计数器；如果一个字符将移出窗口的时候，应该减少window计数器；当valid满足need时应该收缩窗口；应该在收缩窗口前更新最终结果。

下面是完整代码：

```java
class Solution {
    public String minWindow(String s, String t) {
    	HashMap<Character, Integer> need = new HashMap<>();
    	HashMap<Character, Integer> window = new HashMap<>();
    	// 初始化目标字符
	    for (int i = 0; i < t.length(); i++) {
	    	need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
	    }

	    int left = 0, right = 0;
	    int valid = 0;
	    // 记录最小覆盖子串的起始索引及长度
	    int start = 0, len = Integer.MAX_VALUE;
	    while (right < s.length()) {
	        // c 是将移入窗口的字符
	        char c = s.charAt(right);
	        // 右移窗口
	        right++;
	        // 进行窗口内数据的一系列更新
	        if (need.containsKey(c)) {
	            window.put(c , window.getOrDefault(c, 0) + 1);
	            if (window.get(c).equals(need.get(c))) {
                    valid++;
                }                
	        }
            
	        // 判断左侧窗口是否要收缩
	        while (valid == need.size()) {
	            // 在这里更新最小覆盖子串
	            if (right - left < len) {
	                start = left;
	                len = right - left;
	            }
	            // d 是将移出窗口的字符
	            char d = s.charAt(left);
	            // 左移窗口
	            left++;
	            // 进行窗口内数据的一系列更新
	            if (need.containsKey(d)) {
	                if (window.get(d).equals(need.get(d)))
	                    valid--;
	                window.put(d , window.get(d) - 1);
	            }                    
	        }
    }
    // 返回最小覆盖子串
    return len == Integer.MAX_VALUE ?
        "" : s.substring(start, start+len);
    }
}
```
时间复杂度O（n）
空间复杂度O（c）c为字符集大小


这里需要注意的一点是【判断字符相等】，若使用window.get(char)== need.get(char)，会出现leetcode最后一个测试用例【超长字符串】通不过的情况，要明白一件事：
Integer是对象！
Integer会缓存频繁使用的数值，数值范围为-128到127，在此范围内直接返回缓存值，超过该范围就会new 一个对象！此时用“==”判断就会出现字符一样但返回false的情况！
因此判断Integer相等应该用<font color=red>equals()</font>！

回到算法本身，当我们发现某个字符在window的数量满足了need的需要，就要更新valid，表示有一个字符已经满足要求。而且，你能发现，两次对窗口内数据的更新操作是完全对称的。

当valid == need.size()时，说明T中**所有字符**已经被覆盖，已经得到一个可行的覆盖子串，现在应该开始收缩窗口了，以便得到「最小覆盖子串」。

移动left收缩窗口前，窗口内的字符都是可行解，所以应该在收缩窗口的阶段进行最小覆盖子串的更新，以便从可行解中找到长度最短的最终结果。

## 用数组代替哈希表

```java
class Solution {
    public String minWindow(String s, String t) {
    	int[] need = new int[128], window = new int[128];
    	int count = 0;
    	// 初始化目标字符
	    for (int i = 0; i < t.length(); i++) {
	    	need[t.charAt(i)]++;
	    }
	    int left = 0, right = 0;

	    // 记录最小覆盖子串的起始索引及长度
	    int start = 0, len = Integer.MAX_VALUE;
	    while (right < s.length()) {
	        // c 是将移入窗口的字符
	        char c = s.charAt(right);
	        // 右移窗口
	        right++;
	        // 进行窗口内数据的一系列更新
	        if (need[c] > 0) {
	            window[c] += 1;
	            if (window[c] < need[c]) {
                    count++;
                }                
	        }
            
	        // 判断左侧窗口是否要收缩
	        while (count == t.length()) {
	            // 在这里更新最小覆盖子串
	            if (right - left < len) {
	                start = left;
	                len = right - left;
	            }
	            // d 是将移出窗口的字符
	            char d = s.charAt(left);
	            // 左移窗口
	            left++;
	            // 进行窗口内数据的一系列更新
	            if (need[d] > 0) {
	                if (window[d] == need[d])
	                    count--;
	                window[d]--;
	            }                    
	        }
    }
    // 返回最小覆盖子串
    return len == Integer.MAX_VALUE ?
        "" : s.substring(start, start+len);
    }
}
```
空间复杂度O(1)