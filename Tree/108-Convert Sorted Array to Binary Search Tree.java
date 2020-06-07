// Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

// For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

// Example1:

// Given the sorted array: [-10,-3,0,5,9],

// One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

//       0
//      / \
//    -3   9
//    /   /
//  -10  5



// Given the sorted array: [1,2,3,4,5],

// One possible answer is: [3,2,4,1,null,null,5], which represents the following height balanced BST:
//       3
//      / \
//     2   4
//    /     \
//   1  	  5

//  思路
// 使用一个辅助方法来构建二分 查找树，方法中使用中序遍历的方式，递归调用本身来分别创建子树的根节点、左子树根、右子树根。由于数组已经排序，那么中间的数一定是树根。其实二分查找树的构建过程与二分查找某个数的过程是一样的。


 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode head = createBST(nums,0,nums.length-1);
        return head;
    }

    public TreeNode createBST(int[] nums, int low ,int high){
    	if(low > high)//递归的退出条件
    		return null;
    	int mid = (low + high)/2;
    	TreeNode root = new TreeNode(nums[mid]);//创建子树根
    	root.left = createBST(nums , low , mid - 1);//递归创建左子树根
    	root.right = createBST(nums , mid + 1 ,high);
    	return root;

    }	
}