# 题目描述
给定一个二叉树，它的每个结点都存放着一个整数值。

找出路径和等于给定数值的路径总数。

路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020061323350758.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
# 思路1：DFS

本题需要去计算路径和等于给定数值的路径总数，遵循树模型的解题思路，按照递归的方式去求解（递归的一个重要思想就是两部分：1.找到最简单的子问题求解，2.其他问题不考虑内在细节，只考虑整体逻辑），那我们现在来设计递归关系：

首先，最简单的子问题是什么呢？由于这道题是在树的框架下，我们最容易想到的就是遍历的终止条件：

```java
if(root == null){
    return 0;
}
```

接下来，我们来考虑再上升的一个层次，题目要求 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点） 。这就要求我们只需要去求三部分即可：

1. 以当前节点作为头结点的路径数量
2. 以当前节点的左孩子作为头结点的路径数量
3. 以当前节点的右孩子作为头结点的路径数量

将这三部分之和作为最后结果即可。

最后的问题是：我们应该如何去求以当前节点作为头结点的路径的数量？这里依旧是按照树的遍历方式模板，采用**前序遍历**，每到一个节点让sum-root.val，并判断sum是否为0，如果为零的话，则找到满足条件的一条路径。这条路径将与该节点左右子树的统计结果一起相加，往递归的上一层返回。这部分对应解法中的pathSumFrom()方法。

代码如下，形式上是一个“双递归”。

## 代码

```java
public class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) 
            + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614000006642.png)

空间复杂度: O(n)  递归调用栈所花费的空间。
时间复杂度: 
最差情况 O(n^2) 无分支
最好情况 O(nlogn) 平衡树的情况


# 思路2：回溯
上面的解法由于要经过很多重复的分支，因此耗时较长。
如果能用hashMap来记录遍历过程中所经过节点的状态，就能节省很多不必要的重复遍历。
下面转载大神的思路和解法：

这道题用到了一个概念，叫前缀和。就是到达当前元素的路径上，之前所有元素的和。

前缀和怎么应用呢？

如果两个数的前缀总和是相同的，那么这些节点之间的元素总和为零。进一步扩展相同的想法，<font color=red> 如果前缀总和currSum，在节点A和节点B处相差target，则位于节点A和节点B之间的元素之和是target</font>。

因为本题中的路径是一棵树，从根往任一节点的路径上(不走回头路)，有且仅有一条路径，因为不存在环。(如果存在环，前缀和就不能用了，需要改造算法)

每个节点的前缀和，存储在hashMap中，key是前缀和, value是大小为key的前缀和出现的次数。

抵达当前节点(即B节点)后，将前缀和累加，然后查找在前缀和上，有没有前缀和<font color=red> currSum-target </font>的节点(即A节点)，存在即表示从A到B有一条路径之和满足条件的情况。结果加上满足前缀和currSum-target的节点的数量。然后递归进入左右子树。

左右子树遍历完成之后，回到当前层，需要把当前节点添加的前缀和去除。避免回溯之后影响上一层。因为思想是前缀和，不属于前缀的，我们就要去掉它。

时间复杂度：每个节点只遍历一次,O(N).

空间复杂度：开辟了一个hashMap,O(N).

> 作者：burning-summer
> 链接：https://leetcode-cn.com/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/
> 来源：力扣（LeetCode）
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


## 代码

```java
class Solution {
    public int pathSum(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     * @param node 树节点
     * @param prefixSumCount 前缀和Map
     * @param target 目标值
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200614135802891.png)