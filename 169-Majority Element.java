Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2



// 思路1：
// 分治法
class Solution {
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority
        // element.
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }
}



 // 思路2：排序
 // 由题目的性质可以发现，将数组排序后，位于中间的数字一定是主元素。那么使用快排即可。
 // 平均时间复杂度：O(nlgn)



// 思路3：
// 投票算法
// 根据要题目的性质，我们知道所要找的主元素的数量比其他数都多，因此将数字可以分成两类，一类是主元素，另一类是“余数”。
// 用一个标记temp记录某个元素出现的次数，这个元素称为候选者。如果后面的元素和它相同就投票+1，有一个元素和他不相同就投票-1，当temp小于等于0时，重新记录新的元素。


// 时间复杂度：O(n) 空间复杂度：O(1) 简单粗暴
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}

