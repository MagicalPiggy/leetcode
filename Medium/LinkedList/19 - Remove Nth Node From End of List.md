# 题目描述
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？

# 思路
关键词：双指针、哑结点

首先需要找到倒数第N个节点，可以使用双指针法：
两个指针都指向头节点，前指针先走N-1步，然后两指针同时走，当前指针到达最后一个节点，后指针指向的正好就是倒数第n个节点。
下一步就要删除目标，但是我们需要目前的前驱节点才能进行删除，因此，把上一步稍加改进，前指针改为先走N步，那么此时后指针指向的就是目标的前驱了。
还需要解决一个特殊情况就是，需要删除的目标节点正好是链表的头节点，为了找到头节点的前驱，需要使用**哑结点**，将哑结点作为输入链表的头节点，那么在上述的特殊情况中，这个哑结点就是目标节点的前驱节点啦~

如上，只需要对链表进行一趟扫描。

## 代码

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if (head == null) return head;
    	if (head.next == null) return null;
    	// 设置哑节点
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	// 设置前后两个指针
    	ListNode first = dummy, second = dummy;
    	// 先找到倒数第n个节点的前驱,前面的指针先走n步
    	for (int i = 0; i < n ; i++) {
    		first = first.next;
    	}
    	// 两个指针同时往后走
    	// 前指针到达最后一个节点，此时后指针指向的就是倒数第n个节点的前驱
    	while (first.next != null) {
    		first = first.next;
    		second = second.next;
    	}
    	// 删除第n个节点
    	second.next = second.next.next;
    	return dummy.next;
    }
}
```
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：38.1 MB, 在所有 Java 提交中击败了5.43%的用户