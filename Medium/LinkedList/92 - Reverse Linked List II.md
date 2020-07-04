# 题目描述
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL

# 思路
转载[labuladong的题解](https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/)
这个问题其实是在“递归反转链表”这个算法上的扩展。

递归反转整个链表：

```java
ListNode reverse(ListNode head) {
    if (head.next == null) return head;
    ListNode last = reverse(head.next);
    head.next.next = head;
    head.next = null;
    return last;
}

```
## 反转链表前 N 个节点
这次我们实现一个这样的函数：

```java
// 将链表的前 n 个节点反转（n <= 链表长度）
ListNode reverseN(ListNode head, int n)
```

比如说对于下图链表，执行 reverseN(head, 3)：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200704135538205.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
解决思路和反转整个链表差不多，只要稍加修改即可：



```java
ListNode successor = null; // 后驱节点

// 反转以 head 为起点的 n 个节点，返回新的头结点
ListNode reverseN(ListNode head, int n) {
    if (n == 1) { 
        // 记录第 n + 1 个节点
        successor = head.next;
        return head;
    }
    // 以 head.next 为起点，需要反转前 n - 1 个节点
    ListNode last = reverseN(head.next, n - 1);

    head.next.next = head;
    // 让反转之后的 head 节点和后面的节点连起来
    head.next = successor;
    return last;
}    
```

具体的区别：

1、**base case** 变为 **n == 1**，反转一个元素，就是它本身，同时要记录后驱节点。

2、原算法我们直接把 head.next 设置为 null，因为整个链表反转后原来的 head 变成了整个链表的最后一个节点。但现在 head 节点在递归反转之后不一定是最后一个节点了，所以要记录后驱 **successor**（第 n + 1 个节点），反转之后将 head 连接上。
![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070413582464.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
OK，如果这个函数你也能看懂，就离实现「反转一部分链表」不远了。
## 反转链表的一部分
现在解决我们最开始提出的问题，给一个索引区间 [m,n]（索引从 1 开始），仅仅反转区间中的链表元素。

```java
ListNode reverseBetween(ListNode head, int m, int n)
```

首先，如果 m == 1，就相当于反转从链表开头数起的前 n 个元素嘛，也就是我们刚才实现的功能：

```java
ListNode reverseBetween(ListNode head, int m, int n) {
    // base case
    if (m == 1) {
        // 相当于反转前 n 个元素
        return reverseN(head, n);
    }
    // ...
}
```

如果 m != 1 怎么办？若把 head 的索引视为 1，那么我们是想从第 m 个元素开始反转；
如果把 head.next 的索引视为 1 呢？那么相对于 head.next，反转的区间应该是从第 m - 1 个元素开始的；那么对于 head.next.next 呢……

区别于迭代思想，这就是递归思想，所以我们可以完成代码：

```java

ListNode reverseBetween(ListNode head, int m, int n) {
    // base case
    if (m == 1) {
        return reverseN(head, n);
    }
    // 前进到反转的起点触发 base case
    head.next = reverseBetween(head.next, m - 1, n - 1);
    return head;
}
```

至此，我们的最终大 BOSS 就被解决了。

递归的思想相对迭代思想，稍微有点难以理解，处理的技巧是：不要跳进递归，而是利用明确的定义来实现算法逻辑。

处理看起来比较困难的问题，可以尝试化整为零，把一些简单的解法进行修改，解决困难的问题。

## 完整代码

```java
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
	    // base case
	    if (m == 1) {
	        return reverseN(head, n);
	    }
	    // 前进到反转的起点触发 base case
	    head.next = reverseBetween(head.next, m - 1, n - 1);
	    return head;
    }

    ListNode successor = null; // 后驱节点

		// 反转以 head 为起点的 n 个节点，返回新的头结点
	ListNode reverseN(ListNode head, int n) {
	    if (n == 1) { 
	        // 记录第 n + 1 个节点
	        successor = head.next;
	        return head;
	    }
	    // 以 head.next 为起点，需要反转前 n - 1 个节点
	    ListNode last = reverseN(head.next, n - 1);

	    head.next.next = head;
	    // 让反转之后的 head 节点和后面的节点连起来
	    head.next = successor;
	    return last;
	}    
}
```
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：37.3 MB, 在所有 Java 提交中击败了8.70%的用户