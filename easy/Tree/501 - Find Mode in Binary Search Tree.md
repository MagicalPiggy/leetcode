# 题目描述
给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：

结点左子树中所含结点的值小于等于当前结点的值
结点右子树中所含结点的值大于等于当前结点的值
左子树和右子树都是二叉搜索树
例如：
给定 BST [1,null,2,2],

```java
   1
    \
     2
    /
   2
```

返回[2].

提示：如果众数超过1个，不需考虑输出顺序

进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）

# 思路
首先找众数能想到的思路是遍历一遍，统计每个数字出现的次数（hashMap），最后比较即可。
注意到这是一棵<font color=red> 二叉搜索树</font>，那么就要充分利用它的性质，进行<font color=red> 中序遍历</font>，得到的是一个有序的序列，那么相同的数字将连续出现，好处是不需要把每个数字都存下来，只存出现最多的。遍历途中要做的事情就是检查每个数字出现的次数。当发现出现次数更多的，就更新它。

## 代码

```java
class Solution {
    private List<Integer> modes;
    // 当前数字
    private int cur;
    // 当前数字出现的次数
    private int curTimes;
    // 上一个数字出现的次数
    private int lastTimes;

    public int[] findMode(TreeNode root) {
        modes = new LinkedList<>();
        inOrder(root);
        // 将list转为数组
        int[] res = new int[modes.size()];
        for(int i = 0; i < modes.size(); i++)
            res[i] = modes.get(i);
        return res;
    }

    private void inOrder(TreeNode root) {
        if(root == null)    return;
        inOrder(root.left);

        if(lastTimes == 0)
            lastTimes = 1;
        // 新数字，当前次数清零
        if(root.val != cur)
            curTimes = 0;
        // 计数
        cur = root.val;
        curTimes++;
        if(curTimes == lastTimes)   
            modes.add(cur);
        // 出现次数更多，则list清零，添加此数字
        if(curTimes > lastTimes)
        {
            lastTimes = curTimes;
            modes.clear();
            modes.add(cur);
        }

        inOrder(root.right);
    }
}

```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200616150514563.png)