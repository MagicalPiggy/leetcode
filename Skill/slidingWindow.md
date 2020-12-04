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

**其中两处...表示的更新窗口数据的地方**。

而且，这两个`...`处的操作分别是右移和左移窗口更新操作，它们操作是完全对称的。



适用题目：

- [3 - 无重复字符的最长子串](https://github.com/MagicalPiggy/leetcode/blob/master/Medium/String/3%20-%20Longest%20Substring%20Without%20Repeating%20Characters.md)
- [76 - 最小覆盖子串](https://github.com/MagicalPiggy/leetcode/blob/master/Hard/String/76%20-%20Minimum%20Window%20Substring.md)
- [340 - 至多包含 K 个不同字符的最长子串](https://github.com/MagicalPiggy/leetcode/blob/master/Hard/String/340%20-%20Longest%20Substring%20with%20at%20most%20k-distinct%20characters.md)
- [438 - 找到字符串中所有字母异位词](https://github.com/MagicalPiggy/leetcode/blob/master/Medium/String/438%20-%20Find%20All%20Anagrams%20in%20a%20String.md)
- [567 - 字符串的排列 ](https://github.com/MagicalPiggy/leetcode/blob/master/Medium/String/567%20-%20Permutation%20in%20String.md)

