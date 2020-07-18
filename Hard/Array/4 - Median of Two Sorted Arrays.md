# 题目描述
给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

 

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5

# 思路
- 整理自官方题解

给定两个有序数组，要求找到两个有序数组的中位数，最直观的思路有以下两种：

使用<font color=red>**归并**</font>的方式，合并两个有序数组，得到一个大的有序数组。大的有序数组的中间位置的元素，即为中位数。

不需要合并两个有序数组，只要找到中位数的位置即可。由于两个数组的长度已知，因此中位数对应的两个数组的下标之和也是已知的。维护两个指针，初始时分别指向两个数组的下标 00 的位置，每次将指向较小值的指针后移一位（如果一个指针已经到达数组末尾，则只需要移动另一个数组的指针），直到到达中位数的位置。

假设两个有序数组的长度分别为 m 和 n，上述两种思路的复杂度如何？

第一种思路的时间复杂度是 O(m+n)，空间复杂度是 O(m+n)。第二种思路虽然可以将空间复杂度降到 O(1)，但是时间复杂度仍是 O(m+n)。题目要求时间复杂度是 O(log(m+n))，因此上述两种思路都不满足题目要求的时间复杂度。

如何把时间复杂度降低到 O(log(m+n)) 呢？<font color=red>如果对时间复杂度的要求有 log，通常都需要用到二分查找 </font>，这道题也可以通过**二分查找**实现。

根据中位数的定义，当 m+n 是奇数时，中位数是两个有序数组中的第(m+n)/2 个元素，当 m+n 是偶数时，中位数是两个有序数组中的第 (m+n)/2 个元素和第 (m+n)/2+1 个元素的平均值。因此，这道题可以转化成寻找两个有序数组中的第 k 小的数，其中 k 为 (m+n)/2 或 (m+n)/2+1。

假设两个有序数组分别是 A 和 B。
要找到第 k 个元素，我们可以比较 A[k/2−1] 和 B[k/2−1]，其中 / 表示整数除法。由于 A[k/2−1] 和 B[k/2−1] 的前面分别有 A[0..k/2−2] 和 B[0..k/2−2]，即 k/2−1 个元素，对于A[k/2−1] 和B[k/2−1] 中的较小值，最多只会有(k/2−1)+(k/2−1)≤k/2−2 个元素比它小，那么它就不能是第 k 小的数了。

因此我们可以归纳出三种情况：

- 如果 A[k/2−1]<B[k/2−1]，则比 A[k/2−1] 小的数最多只有 A 的前k/2−1 个数和B 的前k/2−1 个数，即比A[k/2−1] 小的数最多只有 k-2 个，因此A[k/2−1] 不可能是第 k 个数，A[0] 到A[k/2−1] 也都不可能是第 k 个数，可以全部排除。

- 同理，如果 A[k/2−1]>B[k/2−1]，则可以排除 B[0] 到 B[k/2−1]。

- 如果A[k/2−1]=B[k/2−1]，则可以归入第一种情况处理。

下图分别展示了三种情况：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200719000423712.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
可以看到，比较 A[k/2−1] 和 B[k/2−1] 之后，<font color=red>可以排除k/2 个不可能是第 k小的数，查找范围缩小了一半</font>。同时，我们将在排除后的新数组上继续进行二分查找，并且根据我们排除数的个数，减少 k 的值，这是因为我们排除的数都不大于第 k 小的数。

有以下三种情况需要特殊处理：

- 如果 A[k/2−1] 或者 B[k/2−1] 越界，那么我们可以选取对应数组中的最后一个元素。在这种情况下，我们必须根据排除数的个数减少 k 的值，而不能直接将 k 减去 k/2。

- 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 k 小的元素。

- 如果 k=1，我们只要返回两个数组首元素的最小值即可。

## 小例子
用一个例子说明上述算法。假设两个有序数组如下：

```java
A: 1 3 4 9
B: 1 2 3 4 5 6 7 8 9
```

两个有序数组的长度分别是 4 和 9，长度之和是 13，中位数是两个有序数组中的第 7 个元素，因此需要找到第k=7 个元素。

比较两个有序数组中下标为k/2−1=2 的数，即 A[2] 和B[2]，如下面所示：


```java
A: 1 3 4 9
       ↑
B: 1 2 3 4 5 6 7 8 9
       ↑
```

由于A[2]>B[2]，因此排除 B[0] 到B[2]，即数组 B 的下标偏移（offset）变为 3，同时更新 k 的值：k=k−k/2=4。

下一步寻找，比较两个有序数组中下标为 k/2−1=1 的数，即A[1] 和 B[4]，如下面所示，其中方括号部分表示已经被排除的数。

```java
A: 1 3 4 9
     ↑
B: [1 2 3] 4 5 6 7 8 9
             ↑
```

由于 A[1]<B[4]，因此排除A[0] 到 A[1]，即数组A 的下标偏移变为 2，同时更新 k的值：k=k−k/2=2。

下一步寻找，比较两个有序数组中下标为k/2−1=0 的数，即比较 A[2] 和B[3]，如下面所示，其中方括号部分表示已经被排除的数。

```java

A: [1 3] 4 9
         ↑
B: [1 2 3] 4 5 6 7 8 9
           ↑
```

由于 A[2]=B[3]，根据之前的规则，排除 A 中的元素，因此排除A[2]，即数组 A 的下标偏移变为 3，同时更新 k 的值： k=k−k/2=1。

由于 k 的值变成 1，因此比较两个有序数组中的未排除下标范围内的第一个数，其中较小的数即为第 k 个数，由于 A[3]>B[3]，因此第 k 个数是B[3]=4。


```java
A: [1 3 4] 9
           ↑
B: [1 2 3] 4 5 6 7 8 9
           ↑
```

## 代码

代码如下：

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        // 奇数的情况
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            // 求第midIndex + 1小的元素
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } 
        // 偶数的情况
        else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            // 求第midIndex + 1小的元素
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        // 两个数组的偏移(offset)
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            // 数组1已经"删除"完毕，那么直接到数组2中找第k小的元素
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            // 数组2已经"删除"完毕，那么直接到数组1中找第k小的元素
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            // 如果 k=1，我们只要返回两个数组首元素的最小值即可
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            
            // 正常情况
            int half = k / 2;
            // newIndex就是数组下标:k/2−1，我们每次循环需要比较两个数组中newIndex处的数字大小
            // 防止数组越界，因此要在index1 + half, length1之间取小值
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;           
            // pivot1与pivot2就是A[k/2−1]、B[k/2−1]
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            // 比较
            if (pivot1 <= pivot2) {
            	// 减小k值,减小的数值为排除的数量
                k -= (newIndex1 - index1 + 1);
                // 移动offset，造成"排除"的效果
                index1 = newIndex1 + 1;
            } else {
            	// 减小k值,减小的数值为排除的数量
                k -= (newIndex2 - index2 + 1);
                // 移动offset，造成"排除"的效果
                index2 = newIndex2 + 1;
            }
        }
    }
}
```
## 复杂度分析

时间复杂度：O(log(m+n))，其中 m 和 n 分别是数组 nums1 和 nums2 的长度。
初始时有 k=(m+n)/2 或 k=(m+n)/2+1，每一轮循环可以将查找范围减少一半，因此时间复杂度是 O(log(m+n))。

空间复杂度：O(1)。