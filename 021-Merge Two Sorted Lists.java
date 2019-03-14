Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// 迭代法，依次取较小的节点连接到新的链表中，需要注意的是当其中一个链表已经遍历完后，连接另一个链表的剩余部分时，只需要连接该剩余部分的头结点即可。
// 时间负责度为O(m+n)


class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	//判断链表是否为空
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0) ;
        ListNode p = head;//p始终指向新链表的末端

        while(l1 != null && l2 != null){
        	if(l1.val < l2.val){
        		p.next = l1;
        		
        		l1 = l1.next;
        	}
        	else{
        		p.next = l2;
        		
        		l2 = l2.next;

        	}
        	p = p.next;
        }
        //连接剩余部分
        if(l1 != null){
        	p.next = l1;
        }
        if (l2 != null) {
        	p.next = l2;
        }
        return head.next;
    }
}

//链表的题目都可以考虑用递归的方法做，可以简化代码
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }
}