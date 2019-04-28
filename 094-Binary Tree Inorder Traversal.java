// Given a binary tree, return the inorder traversal of its nodes' values.

// Example:

// Input: [1,null,2,3]
//    1
//     \
//      2
//     /
//    3

// Output: [1,3,2]
// Follow up: Recursive solution is trivial, could you do it iteratively?

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        inorderVisit(root,list);
        return list;

    }

    public void inorderVisit(TreeNode root,List<Integer> list){
    	if(root != null){
    		inorderVisit(root.left,list);
    		list.add(root.val);
    		inorderVisit(root.right,list);
    	}
    }
}



//非递归中序遍历
public class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}

//线索二叉树
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        List<Integer> res = new ArrayList<Integer>();
        TreeNode pre = null;
        while(root != null){
        	if(root.left == null){
        		res.add(root.val);
        		root = root.right;
        	}else{
        		pre = root.left;
        		while(pre.right != null && pre.right != root){
        			pre = pre.right;
        		}
        		if(pre.right == null){
        			pre.right = root;
        			root = root.left;
        		}else{
        			pre.right = null;
        			res.add(root.val);
        			root = root.right;
        		}
        	}
        }
        return res;
    }
}


