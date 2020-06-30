# 题目描述
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警**。

给定一个代表每个房屋存放金额的非负整数数组，计算你 **不触动警报装置的情况下** ，一夜之内能够偷窃到的最高金额。

Example 1:

    Input: [1,2,3,1]
    Output: 4


示例 1：

```java
输入：[1,2,3,1]
输出：4
```

解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。

示例 2：
```java
输入：[2,7,9,3,1]
输出：12
```

解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。


提示：

0 <= nums.length <= 100
0 <= nums[i] <= 400


# 思路
可以使用以下顺序来处理此特定问题和大多数其他问题：

 1. 找到递归关系
 2. 递归（自上而下）
 3. 递归+备忘录（自上而下）
 4. 迭代+备忘录（自下而上）
 5. 迭代+ N变量（自下而上）


## 步骤1: 找出递归关系
强盗有两种选择：
a）抢劫当前房屋`i`;    
b）不要抢劫当前的房子。

如果选择选项“a”，则意味着她不能抢夺之前的`i-1`号房屋，但可以安全地前往`i-2`房屋，并获得随后的所有累积战利品。
如果选择了一个选项“b”，强盗将从抢劫`i-1`和以下所有建筑中获得所有可能的战利品。

所以问题归结为计算哪种方案得到的东西更多：

 - 抢劫**当前**房屋（i）+**前一次**房屋抢劫（i-2）
 - 从之前的房子抢劫和之前捕获的任何战利品中掠夺(i -1)

将以上描述写成状态转移式，就是本题的关键：

    rob(i) = Math.max( rob(i - 2) + currentHouseValue, rob(i - 1) )

   i表示当前正抢劫第几间房子。

## 步骤2：递归（自上而下）
转换步骤1中的关系应该不是特别困难。

代码如下：

```java
public int rob(int[] nums) {
    return rob(nums, nums.length - 1);
}

private int rob(int[] nums, int i) {
    if (i < 0) {
        return 0;
    }
    return Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
}
```
这样的算法对重复的`rob(i )`计算了多次，如下图，除了绿色的，都是需要重复计算的，算法时间复杂度为O（2^	N）指数级别，有待改进。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630154920794.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

## 步骤3：递归+备忘录（自上而下）
用一个数组memo作为备忘录，memo[i]记录的是rob（i）的值。每次计算rob（i）前检查备忘录是否已经存了该值，如果是，那么直接返回这个值而不需要重复计算了。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630155931244.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)


```java
int[] memo;
public int rob(int[] nums) {
    memo = new int[nums.length + 1];//备忘录
    Arrays.fill(memo, -1);
    return rob(nums, nums.length - 1);
}

private int rob(int[] nums, int i) {
    if (i < 0) {//递归退出条件
        return 0;
    }
    if (memo[i] >= 0) {//如果在备忘录中有纪录，就无须重新计算了
        return memo[i];
    }
    int result = Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
    memo[i] = result;//将每一次的结果存入备忘录
    return result;
}
```
用备忘录来记录已经计算过rob(i)，解决了重复计算的问题，因此算法时间复杂度为O(n)，得到了显著优化。
因为递归堆栈，空间复杂度还是是O(n)，让我们试着改进它，将递归方式改为迭代。

## 步骤4：迭代+备忘录（自下而上）
从前往后开始计算： memo[i+1] = Math.max(memo[i], memo[i-1] + nums[i]
```java
public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    int[] memo = new int[nums.length + 1];
    memo[0] = 0;
    memo[1] = nums[0];
    for (int i = 1; i < nums.length; i++) {
        int val = nums[i];
        memo[i+1] = Math.max(memo[i], memo[i-1] + val);
    }
    return memo[nums.length];
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200630162446336.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
改成迭代之后，时间复杂度不变，节省了递归调用产生的堆栈开销。

## 步骤5：迭代+ 2个变量（自下而上）
我们可以看到，每次循环中我们只使用前面的步骤`memo[i]`和`memo[i-1]`，所以我们只需要记录前2步的结果。我们可以将它们保存在2个变量中。在Fibonacci序列问题和其他一些问题中都可以采用这样的优化。

```java
public int rob(int[] nums) {
    if (nums.length == 0) return 0;

    // prev1 始终保存前一个位置的值
    int prev1 = 0;
    // prev2 始终保存前两个位置的值
    int prev2 = 0;

    for (int num : nums) {
    	// 用临时变量存prev1，因为prev1将在下一步更新
        int tmp = prev1;
        // 用prev2和prev1计算当前位置的值，并且把当前位置的值存于prev1(相对后一个位置，当前值就是prev1)
        prev1 = Math.max(prev2 + num, prev1);
        // prev1将成为prev2
        prev2 = tmp;
    }
    return prev1;
}
```
### 提交结果
Runtime: 2 ms, faster than 100.00% of Java online submissions for House Robber.
Memory Usage: 36.8 MB, less than 46.69% of Java online submissions for House Robber.