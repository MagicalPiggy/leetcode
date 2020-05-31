Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]



class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
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

