# 题目描述
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

```java
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
```

# 思路1：两两合并
参考[21 - 合并两个有序链表](https://github.com/MagicalPiggy/leetcode/blob/master/easy/LinkedList/21%20-%20Merge%20Two%20Sorted%20Lists.md)

合并两个有序链表有两种方法：

- （1）迭代

```java
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
	p.next = l1 != null ? l1 : l2;
       return head.next;
   }

```

- （2）递归

```java
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
```
## 逐一合并
那么此题的K个链表，可以对它们依次进行合并，一共需要进行K-1次合并，时间复杂度O（KN）：

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;
        for (ListNode list: lists) {
            res = mergeTwoLists(res, list); //调用合并2链表的方法
        }
        return res;
    }
}

```
## 分治合并
对 逐一合并 进行优化，用分治的方法进行合并——
- 将 k个链表配对并将同一对中的链表合并；
- 第一轮后，k个链表被合并为k/2个，平均长度为2n/k，继续。。。
- 重复这一过程，直到我们得到了最终的有序链表


![时间复杂度分析：K条链表的总结点数是 N，平均每条链表有 N/K个节点，因此合并**两条链表**的时间复杂度是 O(N/K)。从 K条链表开始两两合并成 1 条链表，因此每条链表都会被合并 logK 次，因此 K 条链表会被合并 K * logK次，因此总共的时间复杂度是 K* logK *N/K 即 O（NlogK）。](https://img-blog.csdnimg.cn/20200709172506860.png)
- 空间复杂度：递归会使用到 O(log k)空间代价的栈空间。

### 分治合并——迭代

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        // 需要合并的链表数量
        int k = lists.length;

        while (k > 1) {
        	// idx指向合并后的链表应该存放的位置
            int idx = 0;
            // 每一轮将k条链表合并为k/2条
            for (int i = 0; i < k; i += 2) {
            	// i指向最后一条链表
                if (i == k - 1) {
                    lists[idx++] = lists[i];
                } else {
                	// 合并两条链表
                    lists[idx++] = mergeTwoLists(lists[i], lists[i + 1]);
                }
            }
            // 重置新的一轮需要合并的链表数量
            k = idx;
        }
        // 合并至仅剩一条链表，结束while循环
        return lists[0];
    }

	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
```
执行用时：2 ms, 在所有 Java 提交中击败了94.90%的用户
内存消耗：42 MB, 在所有 Java 提交中击败了45.59%的用户

### 分治合并——递归

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }


    private ListNode merge(ListNode[] lists, int lo, int hi) {
    	// base case
        if (lo == hi) {
            return lists[lo];
        }
        int mid = lo + (hi - lo) / 2;
        // 递归合并前后两部分
        ListNode l1 = merge(lists, lo, mid);
        ListNode l2 = merge(lists, mid + 1, hi);
        return mergeTwoLists(l1, l2);
    }

	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
```
执行用时：4 ms, 在所有 Java 提交中击败了69.13%的用户
内存消耗：42.7 MB, 在所有 Java 提交中击败了32.35%的用户

# 思路2：K 指针
使用K 个指针分别指向 K 条链表。
## 基础方案
每次 比较 K个指针求 min, 然后选择min节点连接到新链后中，这部分时间复杂度为O(K)，一共N个节点，因此时间复杂度：O(NK)

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) { 
        int k = lists.length;
        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        while (true) {
            ListNode minNode = null;
            int minPointer = -1;
            // 遍历所有的头节点，找到最小的节点
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) {
                    continue;
                }
                if (minNode == null || lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minPointer = i;
                }
            }
            // 没有剩余节点，跳出循环
            if (minPointer == -1) {
                break;
            }

            // 将最小节点连接到新链表后
            **tail.next = minNode;
            tail = tail.next;
            // 最小节点所在链表的表头后移**
            lists[minPointer] = lists[minPointer].next;
        }
        return newHead.next;
    }
}
```
## 优化方案：优先队列
在选取最小元素的时候，我们可以用**优先队列**（利用的是小顶堆来实现）来优化这个过程。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200709173210190.png)

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
    	// 初始化一个优先队列，并指定为小顶堆方式
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);

        // 将所有链表的头节点构造优先队列
        for (ListNode node: lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode newHead = new ListNode(0);
        ListNode tail = newHead;
        while (!pq.isEmpty()) {
        	// 出队节点一定是最小的
            ListNode minNode = pq.poll();
            // 连接到新链表后
            tail.next = minNode;
            tail = minNode;
            // 最小节点所在链表表头后移(重新入队)
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return newHead.next;
    }
}

```