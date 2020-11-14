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

