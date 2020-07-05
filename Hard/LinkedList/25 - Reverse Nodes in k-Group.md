# 题目描述
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。


示例：

给你这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5


说明：

- 你的算法只能使用常数的额外空间。
- 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

# 思路
采用递归算法**reverseKGroup**，该算法的定义是：
**输入一个节点 head和一个整数k，将「从head 开始的k个节点」的链表反转，并返回反转之后的头结点。**

每一次进入递归所做的事情如下：
1、找到待翻转的k个节点（注意：若剩余数量小于 k 的话，则不需要反转，因此直接返回待翻转部分的头结点即可）。
2、调用辅助方法reverse()对其进行翻转。并返回翻转后的头结点
（注意：翻转为左闭右开区间，所以本轮操作的尾结点其实就是下一轮操作的头结点）。
3、递归调用**reverseKGroup**，传入本轮尾节点。
4、本轮进行翻转后的尾结点连接到步骤3中递归的返回值（也就是下一轮翻转后的新的头节点）。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200705152830708.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

时间复杂度：O(n)，其中 n 为链表的长度。tail指针会在 O(⌊ n/k ⌋) 个结点上停留，每次停留需要进行一次 O(k)的翻转操作。

空间复杂度：O(1，我们只需要建立常数个变量。

## 代码

```java
class Solution {
       public ListNode reverseKGroup(ListNode head, int k) {
   		// 空链表或单节点链表直接返回
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            //剩余数量小于k的话，则不需要反转。
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        // 反转head到tail这段链表(head开始的前k个元素)
        ListNode newHead = reverse(head, tail);
        //下一轮的开始的地方就是tail，此时的head为上一段翻转后的尾节点，将其连接到下一段的newHead
        head.next = reverseKGroup(tail, k);

        return newHead;
    }

    /*
    翻转curr到tail这段链表(不包括tail);返回翻转后新的头节点
     */
    private ListNode reverse(ListNode curr, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (curr != tail) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

}
```
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：39.5 MB, 在所有 Java 提交中击败了7.32%的用户