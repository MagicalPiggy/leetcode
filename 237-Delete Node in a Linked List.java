// Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

// Given linked list -- head = [4,5,1,9], which looks like following:

// 4->5->1->9

// Example 1:

// Input: head = [4,5,1,9], node = 5
// Output: [4,1,9]
// Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.
// Example 2:

// Input: head = [4,5,1,9], node = 1
// Output: [4,5,9]
// Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.
 

// Note:

// The linked list will have at least two elements.
// All of the nodes' values will be unique.
// The given node will not be the tail and it will always be a valid node of the linked list.
// Do not return anything from your function.


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// 思路：
// 一般删除链表的思路是，将需要删除的A节点的前驱节点F.next指向A.next即可。
// 本题的特殊之处是传入的参数没有头结点，而是直接把将要删除的目标节点传入，这样一来就没办法找到该节点的前驱节点了。
// 那么可以换一种方式，不使用前驱节点，而是直接利用该节点A——
// 将该节点A赋值为其后继节点B的值，那么这时候删除哪一个都是可行的，显然，我们可以删除B


class Solution {
    public void deleteNode(ListNode node) {
    	public void deleteNode(ListNode node) {
	    node.val = node.next.val;
	    node.next = node.next.next;
 	}
    
}
