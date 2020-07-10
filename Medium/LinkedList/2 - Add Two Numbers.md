# 题目描述
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

# 思路
这题的难点在于需要处理**进位**，如果两个链表长度不一致，出现最高位的进位时，处理起来比较棘手。

因此，我们可以将两个链表视为长度一致，那么当同时遍历两条链表时，短的一条会出现空节点，此时我们给它进行“**补0**”即可。既解决了长度问题，右不影响数字相加的结果。

当前位计算的同时需要考虑上一位的进位，而当前位计算结束后同样需要更新进位值。

两个链表全部遍历完毕后，进位值为 1，则在新链表最后添加一个值位1的节点。

## 代码

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	// 链表构造过程需要指针移动，因此需要一个预先节点来返回链表
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null) {
        	// 短的链表补0
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;
            
            // 进位
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;
        }
        // 如果两个链表全部遍历完毕后，进位值为 1，则在新链表最后添加节点1
        if(carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}

```

执行用时：2 ms, 在所有 Java 提交中击败了99.85%的用户
内存消耗：40.1 MB, 在所有 Java 提交中击败了93.78%的用户

优化一下使得代码更精简：

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	// 链表构造过程需要指针移动，因此需要一个预先节点来返回链表
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while(l1 != null || l2 != null || carry > 0) {
        	int sum = carry;
        	// 短的链表补0
            sum += l1 == null ? 0 : l1.val;
            sum += l2 == null ? 0 : l2.val;
            
            cur.next = new ListNode(sum % 10);

            cur = cur.next;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;

            // 进位
            carry = sum / 10;
        }

        return pre.next;
    }
}
```