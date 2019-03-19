// Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

// Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

// Example 1:

// Given nums = [1,1,2],

// Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

// It doesn't matter what you leave beyond the returned length.
// Example 2:

// Given nums = [0,0,1,1,1,2,2,3,3,4],

// Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

// It doesn't matter what values are set beyond the returned length.
// Clarification:

// Confused why the returned value is an integer but your answer is an array?

// Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

// Internally you can think of this:

// // nums is passed in by reference. (i.e., without making a copy)
// int len = removeDuplicates(nums);

// // any modification to nums in your function would be known by the caller.
// // using the length returned by your function, it prints the first len elements.
// for (int i = 0; i < len; i++) {
//     print(nums[i]);
// }

class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length==0)
            return 0;
        
        int dupNum = 0;//数组中“多余”元素的数量
        int newLength = 1;//保存修改后数组的长度
        int i = 0;
        
        while(i < nums.length-1){
            while(nums[i] == nums[i+1]){//后边的元素与当前元素相同，则i后移直到停留在不同的元素之前
                dupNum++; //说明后边的元素是多余的
                i++;
                if(i == nums.length-1)//i到达数组的末尾则直接返回，避免越界
                    return newLength;
            }
            
            nums[i+1-dupNum] = nums[i+1]; //关键，向前替换掉对应位置的元素，而无需整体移动
            newLength++;
            i++;
     }
        return newLength;
    }
}

class Solution {
	public int removeDuplicates(int[] nums) {
	    if (nums.length == 0) return 0;
	    int i = 0;
	    
	    for (int j = 1; j < nums.length; j++) {
	        if (nums[j] != nums[i]) {
	            i++;
	            nums[i] = nums[j];
	        }
	    }
	    return i + 1;
	}
}


