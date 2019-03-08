// Reverse a singly linked list.

// Example:

// Input: 1->2->3->4->5->NULL
// Output: 5->4->3->2->1->NULL
// Follow up:

// A linked list can be reversed either iteratively or recursively. Could you implement both?



//迭代法-思路：
// 从前往后依次处理,newHead指针始终指向新链表的表头，
// p为工作指针，指向原链表的表头，将p连接到新链表后，使p往后移动，
// 这里先要使用一个临时指针tmp指向p.next,否则p.next直接指向新链表会造成原链表的丢失
//当p移动到null时停止迭代
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null||head.next == null)//链表为空或仅有一个数的情况，直接返回
            return null;
        
	    ListNode p = head;
	    ListNode newHead = null;
	    

		while(p != null)//一直迭代到链尾
		{
		   	ListNode tmp = p.next;//临时指针指向p的下一个地址防止丢失
		   	p.next = newHead; //p.next指向新的表头
		   	newHead = p; //移动newHead使其始终指向新连接的节点，使新链表延长
		   	p = tmp;//p指向原来链表的下一个节点
		}
		return newHead;
    	
        
    }

}

//递归法-思路：
// 与迭代法的区别是从后往前处理，以1->2->3->4->5->NULL为例，先通过递归调用使newHead指向5(之后newHead将一直指向5，因为这是新链表的表头)，
// 当递归返回上一层时head指向4，此时应该让5的next指向4，即head.next.next = head;
// 此时链表是这样的：1->2->3->4<->5;这时应该断开原来4.next的连接，使head.next=null；这样做是为了让递归结束时，处于链表最后的1的next指向null，否则其仍指向2将产生循环。
// 同理，head逐层返回后做出同样的处理，那么新链表将从后往前延长：1->2->3<-4<-5;
// 直到最后:null<-1<-2<-3<-4<-5
						 


class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;

    }
}


