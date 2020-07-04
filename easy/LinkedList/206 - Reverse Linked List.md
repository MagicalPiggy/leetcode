# 题目描述

反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？


# 迭代法-思路：
**从前往后**依次处理,newHead指针始终指向新链表的表头，
 p为工作指针，指向原链表的表头，将p连接到新链表后，使p往后移动， 这里先要使用一个临时指针 tmp指向p.next,否则p.next 直接指向新链表会造成原链表的丢失。
当p移动到null时停止迭代。

## 代码

```java
class Solution {
    public ListNode reverseList(ListNode head) {
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
执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：39.5 MB, 在所有 Java 提交中击败了5.06%的用户


# 递归法-思路：
与迭代法的区别是**从后往前**处理，以1->2->3->4->5->NULL为例，先通过递归调用使newHead指向5(之后newHead将一直指向5，因为这是新链表的表头)，
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200703151826343.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
当递归返回上一层时head指向4，此时应该让5的next指向4，即head.next.next = head;

此时链表是这样的：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200703151904785.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
这时应该断开原来4.next的连接，使head.next=null；这样做是为了让递归结束时，处于链表最后的1的next指向null，否则其仍指向2将产生循环。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200703151946580.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

 同理，head逐层返回后做出同样的处理，那么新链表将从后往前延长：
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200703152126292.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

 直到最后:
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200703152250234.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

## 代码

```java
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
```
执行用时：
0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：40.1 MB, 在所有 Java 提交中击败了5.06%的用户

# 递归法再梳理
看了大佬@labuladong的题解，发现自己对递归的理解还是太肤浅了。
**用递归处理的技巧是：不要跳进递归，而是利用明确的定义来实现算法逻辑。**
上文对递归算法的解释就是跳进了递归，其实并不是太直观，而且人脑压栈确实比较吃力。
下面转载大佬的题解。
[原文链接](https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/)

递归反转整个链表，这个算法可能很多读者都听说过，这里详细介绍一下，先直接看实现代码：

```java
ListNode reverse(ListNode head) {
    if (head.next == null) return head;
    ListNode last = reverse(head.next);
    head.next.next = head;
    head.next = null;
    return last;
}
```

看起来是不是感觉不知所云，完全不能理解这样为什么能够反转链表？这就对了，这个算法常常拿来显示递归的巧妙和优美，我们下面来详细解释一下这段代码。

**对于递归算法，最重要的就是明确递归函数的定义**。具体来说，我们的 reverse 函数定义是这样的：

**输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头结点**。

明白了函数的定义，在来看这个问题。比如说我们想反转这个链表：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200704133812167.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
那么输入 reverse(head) 后，会在这里进行递归：

```java
ListNode last = reverse(head.next);
```
**不要跳进递归**（你的脑袋能压几个栈呀？），而是要根据刚才的函数定义，来弄清楚这段代码会产生什么结果：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200704134046828.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
这个 **reverse(head.next)** 执行完成后，整个链表就成了这样：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200704134114390.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
并且根据函数定义，reverse 函数会返回反转之后的头结点，我们用变量 last 接收了。

现在再来看下面的代码：

```java
head.next.next = head;
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/2020070413415055.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
接下来：

```java
head.next = null;
return last;
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200704134212934.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
神不神奇，这样整个链表就反转过来了！递归代码就是这么简洁优雅，不过其中有两个地方需要注意：

1、递归函数要有 base case，也就是这句：


```java
if (head.next == null) return head;
```

意思是如果链表只有一个节点的时候反转也是它自己，直接返回即可。

2、当链表递归反转之后，新的头结点是 last，而之前的 head 变成了最后一个节点，别忘了链表的末尾要指向 null：

```java

head.next = null;
```