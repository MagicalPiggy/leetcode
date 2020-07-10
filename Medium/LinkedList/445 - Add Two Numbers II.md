# 题目描述
给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

 

进阶：

**如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。**

 

示例：

输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 8 -> 0 -> 7

# 思路1：翻转链表
这题是[leetcode-2 Add Two Numbers（两数相加）](https://github.com/MagicalPiggy/leetcode/blob/master/Medium/LinkedList/2%20-%20Add%20Two%20Numbers.md)的延伸，该题的处理顺序是从低位到高位进行相加操作，这样的好处是，对于进位的处理比较方便，因为进位本来就是从低位向高位的。这题的难点也就在于从高位向低位如何处理。

其实题目也进行了提示，我们可以先对输入链表进行翻转，那么就能转化为[leetcode-2 Add Two Numbers（两数相加）](https://github.com/MagicalPiggy/leetcode/blob/master/Medium/LinkedList/2%20-%20Add%20Two%20Numbers.md)，又变成从低位到高位处理了。最后只需要把结果链表再进行一次翻转就是正确答案了。

## 代码

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	if (l1 == null && l2 == null) return null;
    	l1 = reverseList(l1);
    	l2 = reverseList(l2);

    	return reverseList(addTwoList(l1,l2));
    }

    private ListNode addTwoList(ListNode l1, ListNode l2) {
    	ListNode newHead = new ListNode(0);
    	ListNode cur = newHead;
    	// 进位
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
    	return newHead.next;

    }

    // 翻转链表并返回翻转后的头节点
    private ListNode reverseList(ListNode head) {
    	if (head == null || head.next == null) return head;
    	ListNode tail = null;
    	while(head != null) {
    		ListNode temp = head.next;
    		head.next = tail;
    		tail = head;
    		head = temp;
    	}
    	return tail;
    }
}
```
执行用时：3 ms, 在所有 Java 提交中击败了89.75%的用户
内存消耗：39.7 MB, 在所有 Java 提交中击败了95.83%的用户

# 思路2：栈
题目给了提示，也给了挑战——这题可以不需要翻转就能处理。
不翻转怎么进行逆序处理呢？自然就能想到使用**栈**了——栈总是与逆序息息相关。
我们先把输入链表存入两个栈中，那么出栈顺序就是原来的倒序了，如此一来，又实现了从低位往高位处理。

## 代码

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) { 
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        // 将L1入栈
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        // 将L2入栈
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        
        int carry = 0;
        ListNode head = null;

        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            sum += stack1.isEmpty()? 0: stack1.pop();
            sum += stack2.isEmpty()? 0: stack2.pop();
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            carry = sum / 10;
        }
        return head;
    }
}
```
执行用时：
5 ms, 在所有 Java 提交中击败了65.19%的用户
内存消耗：40 MB, 在所有 Java 提交中击败了95.83%的用户