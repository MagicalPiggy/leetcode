// Write a program to find the node at which the intersection of two singly linked lists begins.

// For example, the following two linked lists:


// begin to intersect at node c1.

 

// Example 1:


// Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
// Output: Reference of the node with value = 8
// Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 

// Example 2:


// Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
// Output: Reference of the node with value = 2
// Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 

// Example 3:


// Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
// Output: null
// Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
// Explanation: The two lists do not intersect, so return null.
 

// Notes:

// If the two linked lists have no intersection at all, return null.
// The linked lists must retain their original structure after the function returns.
// You may assume there are no cycles anywhere in the entire linked structure.
// Your code should preferably run in O(n) time and use only O(1) memory.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// 首先遍历一遍两条链表，直到其中一个指针到达末尾，这时候有三种情况：

// a还未到达末尾，说明A链表比B链表长
// b还未到达末尾，说明B链表比A链表长
// 同时到达末尾，说明两根链表一样长
// 对于情况1，继续将a往后移动同时将A链表的头指针往后移动，当a到达末尾时，从headA算起，A链和B链一样长。此时再同时移动headA和headB，判断指针是否相同，相同即为交叉点并返回

// 情况2同理
// 情况3则直接进行头指针移动

// 时间复杂度O(n)
// 空间复杂度O(1)
// --------------------- 
// 作者：Mr.Bean-Pig 
// 来源：CSDN 
// 原文：https://blog.csdn.net/z714405489/article/details/89211783 
// 版权声明：本文为博主原创文章，转载请附上博文链接！

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	ListNode a = headA,b = headB;
    	int step = 0;
    	while(a != null && b != null){
    		a = a.next;
    		b = b.next;
    	}
        
        while(a != null){
        	a = a.next;
        	headA = headA.next;
        }
        
        while(b != null){
        	b = b.next;
        	headB = headB.next;
        }

        while(headA != null || headB != null){
        	if(headA == headB) return headA;
        	else{
        		headA = headA.next;
        		headB = headB.next;
        	}

        }
        return null;
    }
}