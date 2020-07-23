# 题目描述
难度：hard
给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
求在该柱状图中，能够勾勒出来的矩形的最大面积。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200723133215574.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200723133236733.png)
图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

示例:

```java
输入: [2,1,5,6,2,3]
输出: 10
```

# 思路1：暴力求解
可以遍历每根柱子，以当前柱子 i 的高度作为矩形的高，那么矩形的宽度边界即为向左找到第一个高度**小于**当前柱体 i 的柱体，向右找到第一个高度小于当前柱体 i 的柱体。这样就能求出当前柱子为高的矩形面积。

对于每个柱子我们都如上计算一遍以当前柱子作为高的矩形面积，最终比较出最大的矩形面积即可。

时间复杂度：O（n^2）。遍历数组O(n)，其中嵌套一个O(n)用于向左右搜索边界。
空间复杂度：O (1)

## 代码

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
    	int maxRes = 0;
    	for(int i = 0; i < heights.length; i++) {
    		int height = heights[i];
    		int left = i, right = i;
    		// 获取左边第一个比当前低的柱体
    		while(left >= 0 && heights[left] >= height) {
    			left--;
    		}
    		// 获取右边第一个比当前低的柱体
    		while(right < heights.length && heights[right] >= height) {
    			right++;
    		}
    		// 计算面积
    		int res = height * (right - left - 1);
    		maxRes = maxRes < res ? res : maxRes; 
    	}
    	return maxRes;
    }
}
```
执行用时：776 ms, 在所有 Java 提交中击败了29.94%的用户
内存消耗：41.5 MB, 在所有 Java 提交中击败了17.39%的用户

# 思路2：单调栈
上面的解法中，每次找当前矩形的宽度边界需要O（n），我们可以想办法优化成O（1），那么在遍历数组的过程中，就必须要记录与宽度有关的信息（高度由于可以直接用下标取，所以无需保存），换句话说，需要记录左边第一个高度小于自身的柱体。

如何实现？可以使用<font color=red>**单调增栈** </font>，因为对于栈中的柱体来说，**栈中下一个柱体就是左边第一个高度小于自身的柱体**。

具体操作过程是这样的，我们遍历每个柱体，若当前的柱体高度大于等于栈顶柱体的高度，就直接将当前柱体入栈，否则若当前的柱体高度**小于**栈顶柱体的高度，说明<font color=red>当前栈顶柱体找到了右边的第一个小于自身的柱体 </font>（这里就可确定宽度的右边界），那么就可以将栈顶柱体出栈（栈顶的下一个柱体就是宽度的左边界）来计算以其为高的矩形的面积了。

时间复杂度：O(n) 
空间复杂度：O(n) 
## 代码

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        // 这里为了代码简便，在柱体数组的头和尾加了两个高度为 0 的柱体。
        int[] tmp = new int[heights.length + 2];
        System.arraycopy(heights, 0, tmp, 1, heights.length); 
        // 栈中存的是坐标
        Stack<Integer> stack = new Stack<>();
        int area = 0;
        for (int i = 0; i < tmp.length; i++) {
            // 对栈中柱体来说，栈中的下一个柱体就是其「左边第一个小于自身的柱体」；
            // 若当前柱体 i 的高度【小于】栈顶柱体的高度，说明 i 是栈顶柱体的「右边第一个小于栈顶柱体的柱体」。
            // 因此以【栈顶】柱体为高的矩形的左右宽度边界就确定了，可以计算面积
            while (!stack.isEmpty() && tmp[i] < tmp[stack.peek()]) {
            	// 计算栈顶柱的面积
                int curHeight = tmp[stack.pop()];
                area = Math.max(area, (i - stack.peek() - 1) * curHeight);   
            }
            stack.push(i);
        }

        return area;
    }
}
```
执行用时：12 ms, 在所有 Java 提交中击败了75.91%的用户
内存消耗：41.2 MB, 在所有 Java 提交中击败了39.13%的用户