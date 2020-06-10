Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
	    List<List<Integer>> list = new ArrayList<>();
	    //Arrays.sort(nums);
	    backtrack(list, new ArrayList<>(), nums, 0);
	    return list;
	}

	private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
	    list.add(new ArrayList<>(tempList));//把子数组添加到最终结果集中
	    
	    for(int i = start; i < nums.length; i++){
	        tempList.add(nums[i]);
	        
	        backtrack(list, tempList, nums, i + 1);
	        tempList.remove(tempList.size() - 1);
	        }
    }
}

// [[]]
// [1]
// [[], [1]]
// [1, 2]
// [[], [1], [1, 2]]
// [1, 2, 3]
// [[], [1], [1, 2], [1, 2, 3]]
// [1, 3]
// [[], [1], [1, 2], [1, 2, 3], [1, 3]]
// [2]
// [[], [1], [1, 2], [1, 2, 3], [1, 3], [2]]
// [2, 3]
// [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3]]
// [3]
// [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]


class Solution {
    public List<List<Integer>> subsets(int[] nums) {
	    List<List<Integer>> list = new ArrayList<>();
	    //Arrays.sort(nums);
	    List<Integer> tempList = new ArrayList<>();
	    backtrack(list, tempList, nums, 0);
	    return list;
	}

	private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
	    list.add(new ArrayList<>(tempList));
	    
	    for(int i = start; i < nums.length; i++){
	        tempList.add(nums[i]);
	        
	        backtrack(list, tempList, nums, i + 1);
	        tempList.remove(tempList.size() - 1);//剪枝
	        }
    }
}