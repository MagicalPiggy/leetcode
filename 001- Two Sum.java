// Given an array of integers, return indices of the two numbers such that they add up to a specific target.

// You may assume that each input would have exactly one solution, and you may not use the same element twice.

// Example:

// Given nums = [2, 7, 11, 15], target = 9,

// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].


class Solution {
    public int[] twoSum(int[] nums, int target) {
    	int[] res = new int[2];
        List<Integer> list = new ArrayList<Integer>(nums.length);
        for(int i = 0;i < nums.length ; i++){
        	if(list.contains(target-nums[i])){
        		res[0] = list.indexOf(target-nums[i]);
        		res[1] = i;
        		
        	}
        	
        	list.add(nums[i]);
        }
        return res;
    }
}


class Solution {
public int[] twoSum(int[] nums, int target) {
	int[] result = new int[2];
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
             result[0] = map.get(complement);
             result[1] = i;
        }
        map.put(nums[i], i);
    }
    //throw new IllegalArgumentException("No two sum solution");
    return result;
}
}


//https://blog.csdn.net/z714405489/article/details/88615195