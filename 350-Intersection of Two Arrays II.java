// 求两数组交集

// Given two arrays, write a function to compute their intersection.

// Example 1:

// Input: nums1 = [1,2,2,1], nums2 = [2,2]
// Output: [2,2]
// Example 2:

// Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
// Output: [4,9]
// Note:

// Each element in the result should appear as many times as it shows in both arrays.
// The result can be in any order.
// Follow up:

// What if the given array is already sorted? How would you optimize your algorithm?
// What if nums1's size is small compared to nums2's size? Which algorithm is better?
// What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

// 思路
// 值得注意的是，题目中说结果可以是无序的，这意味着算法执行过程中可以改变原数组的顺序，自然就想到通过排序解决：先将两个数组进行排序，然后使用双指针法依次比对两个数组——

// 当各自指针指向的元素相等时，保存下来（因为长度是动态的，所以使用ArrayList这个集合来保存），并将两个指针同时往后移动
// 当元素不相等时，只需要移动较小元素的指针即可
// 排序操作决定了时间复杂度：O(nlgn)


class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
    	//先对两个数组进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList result = new ArrayList();//保存交集中的uansu
        for(int i = 0, j = 0; i < nums1.length && j < nums2.length ;){
        	if(nums1[i] == nums2[j]){//元素相等
        		result.add(nums1[i]);//保存
        		i++;//同时移动索引进行后面的比较
        		j++;
        	}
        	else if(nums1[i] < nums2[j])
        		i++;
        	else
        		j++;

        }

        //把 ArrayList中的元素转换为数组返回
        int[] res = new int[result.size()];
        for(int i = 0; i < res.length; i++){
        	res[i] = (int)result.get(i);
        }
        return res;

    }
}