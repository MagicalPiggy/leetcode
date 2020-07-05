# 题目描述
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1:

给定链表 1->2->3->4, 重新排列为 1->4->2->3.
示例 2:

给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.

# 思路1：拆逆合
三步走，举个例子。

```java
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

第一步，将链表平均分成两半

```java
1 -> 2 -> 3
4 -> 5 -> 6
```

第二步，将第二个链表逆序

```java
1 -> 2 -> 3
6 -> 5 -> 4 (逆序后)
```

第三步，交替连接两个链表

```java
1 -> 6 -> 2 -> 5 -> 3 -> 4
```

第一步找中点，使用快慢指针。快指针一次走两步，慢指针一次走一步，当快指针走到终点，慢指针会刚好到中点。如果节点个数是偶数的话，slow 走到的是左端点，利用这一点，我们可以把奇数和偶数的情况合并，将slow.next视为下半段的头节点，不需要分开考虑（如果是奇数，那么后半段较短）。

第二步链表逆序使用迭代进行。

第三步则两个指针分别向后移动。

## 代码
```java
class Solution {
public void reorderList(ListNode head) {
    if (head == null || head.next == null || head.next.next == null) {
        return;
    }
    //找中点，链表分成两个
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    // newHead为后半段的头结点
    ListNode newHead = slow.next;
    // 断开与后半段的连接
    slow.next = null;
    
    //第二个链表倒置，newHead为倒置后的头节点
    newHead = reverseList(newHead);
    
    //链表节点依次连接
    while (newHead != null) {
        ListNode temp = newHead.next;
        newHead.next = head.next;
        head.next = newHead;
        head = newHead.next;
        newHead = temp;
    }

}

private ListNode reverseList(ListNode head) {
    if(head == null||head.next == null)//链表为空或仅有一个数的情况，直接返回
        return head;
    // 工作指针
    ListNode p = head;
    // 新链表的表头
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
```
执行用时：
2 ms, 在所有 Java 提交中击败了81.08%的用户
内存消耗：42.7 MB, 在所有 Java 提交中击败了9.09%的用户

# 思路2：存起来
链表的缺点就是不能随机存储，当我们想取末尾元素的时候，只能从头遍历一遍，很耗费时间。第二次取末尾元素的时候，又得遍历一遍。
所以可以把链表存储到线性表中，然后用双指针依次从头尾取元素即可。当然这种方法十分耗费空间。
## 代码

```java
public void reorderList(ListNode head) {
    if (head == null) {
        return;
    }
    //存到 list 中去
    List<ListNode> list = new ArrayList<>();
    while (head != null) {
        list.add(head);
        head = head.next;
    }
    //头尾指针依次取元素
    int i = 0, j = list.size() - 1;
    while (i < j) {
    	// 头指针直接连接到尾指针
        list.get(i).next = list.get(j);
        // 头指针后移
        i++;
        //偶数个节点的情况，会提前相遇
        if (i == j) {
            break;
        }
        // 尾指针连到后移的头指针
        list.get(j).next = list.get(i);
        // 尾指针前移
        j--;
    }
    // 最后一个指针指向null
    list.get(i).next = null;
}
```
执行用时：
2 ms, 在所有 Java 提交中击败了81.08%的用户
内存消耗：43.8 MB, 在所有 Java 提交中击败了9.09%的用户