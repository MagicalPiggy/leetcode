# 题目

将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树： 


          0
         / \
       -3   9
       /   /
     -10  5
# 思路
使用一个辅助方法来构建二分 查找树，方法中使用中序遍历的方式，递归调用本身来分别创建子树的根节点、左子树根、右子树根。由于数组已经排序，那么中间的数一定是树根。其实二分查找树的构建过程与二分查找某个数的过程是一样的。

# 代码

```java
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
```
Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
Memory Usage: 37.9 MB, less than 39.69% of Java online submissions for Convert Sorted Array to Binary Search Tree.