# 题目描述
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:

输入: 4->2->1->3
输出: 1->2->3->4
示例 2:

输入: -1->5->3->4->0
输出: -1->0->3->4->5

# 思路1：递归
如果不要求O（1）的空间复杂度，仅仅考虑 O(n log n) 时间复杂度，那么可以采用归并排序。
注意：递归调用函数将带来O(logn)的空间复杂度。

首先将链表分割，关键是找中点，通过快慢指针的方法找到中点，进行分割。
接着递归地往下分割，直到无法再分，往上层返回，在上层将有序链表合并。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710205322468.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
（图片来自Krahets的题解）

## 代码

```java
public class Solution {
  
  public ListNode sortList(ListNode head) {
  	// 1、递归结束条件
    if (head == null || head.next == null)
      return head;
        
    // 2、使用双指针法找到中间节点，并将链表分为前后两部分
    ListNode prev = null, slow = head, fast = head;
    
    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    // pre为中间节点（前半部分的最后一个节点）
    prev.next = null;
    
    // 3、每个部分分别排序
    ListNode l1 = sortList(head);
    ListNode l2 = sortList(slow);
    
    // 4、合并已经有序的两部分链表
    return merge(l1, l2);
  }
  
  // 用于合并有序的两个链表
  ListNode merge(ListNode l1, ListNode l2) {
    ListNode newHead = new ListNode(0), p = newHead;
    
    while (l1 != null && l2 != null) {
      if (l1.val < l2.val) {
        p.next = l1;
        l1 = l1.next;
      } else {
        p.next = l2;
        l2 = l2.next;
      }
      p = p.next;
    }
    
    p.next = l1 != null ? l1 : l2;  
    return newHead.next;
  }

}
```

执行用时：4 ms, 在所有 Java 提交中击败了61.20%的用户
内存消耗：41.4 MB, 在所有 Java 提交中击败了5.88%的用户

# 思路2：非递归（从底至顶直接合并）
上面已经分析过，递归的算法时间复杂度无法满足常数级别，因此要考虑非递归的解法。

对于**非递归**的归并排序，需要使用迭代的方式替换cut环节：

我们知道，cut环节本质上是通过**二分法**得到链表最小节点单元，再通过多轮合并得到排序结果。

每一轮合并merge操作针对的单元都有固定长度intv，例如：
第一轮合并时 i = 1，即将整个链表切分为多个长度为1的单元，并按顺序两两排序合并，合并完成的已排序单元长度为2。
第二轮合并时 i = 2，即将整个链表切分为多个长度为2的单元，并按顺序两两排序合并，合并完成已排序单元长度为4。
以此类推，直到单元长度 **i**  >= 链表长度，代表已经排序完成。

根据以上推论，我们可以仅根据 **i** 计算每个单元边界，并完成链表的每轮排序合并，例如:

当 i  = 1时，将链表第1和第2节点排序合并，第3和第4节点排序合并，……。
当 i  = 2时，将链表第1-2和第3-4节点排序合并，第5-6和第7-8节点排序合并，……。
当 i  = 4时，将链表第1-4和第5-8节点排序合并，第9-12和第13-16节点排序合并，……。

整个链表总共遍历 logn 次，每次遍历的复杂度是 O(n)，所以总时间复杂度是 O(nlogn)；
整个算法没有递归，迭代时只会使用常数个额外变量，所以额外空间复杂度是O(1).

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200710214800666.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
（以上整理自Krahets的题解）

```java
class Solution {
    public ListNode sortList(ListNode head) {
        // 归并排序
        int n = 0;
        // 求链表的长度n
        for(ListNode i = head; i != null; i=i.next) n++;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        
        // 第一层循环，分块，从1个一块，2个一块，4个一块，直到n个一块，
        for(int i = 1; i < n; i = 2*i){
            ListNode begin = dummy;
            // 开始归并
            // j + i < n 表示只有一段就不归并了，因为已经是排好序的
            // 第二层循环，将此时的最小单元排序合并
            for(int j = 0; j + i < n; j = j + 2 * i){
                // 两块，找两块的起始节点
                // 开始都指向第一块的起点
                // 然后second走n步指向第二块的起点
                ListNode first = begin.next, second = first;
                for(int k = 0; k < i; k++) second = second.next;

                // 遍历第一块和第二块进行归并
                // 第一块的数量为i
                // 第二块的数量为i也可能小于i，所以循环条件要加一个second != null
                int f = 0, s = 0;
                while(f < i && s < i && second != null){
                    if(first.val < second.val){
                        begin.next = first;
                        begin = begin.next;
                        first = first.next;
                        f++;
                    }else{
                        begin.next = second;
                        begin = begin.next;
                        second = second.next;
                        s++;
                    }
                }
                // 归并之后可能有多余的没有处理
                // 处理第一块的剩余部分
                while(f < i){
                    begin.next = first;
                    begin = begin.next;
                    first = first.next;
                    f++;

                }
                // 处理第二块的剩余部分
                while(s < i && second != null){
                    begin.next = second;
                    begin = begin.next;
                    // second已经更新到下一块的起点了
                    second = second.next;
                    s++;
                }

                // 更新begin
                // begin.next 指向下一块的起点
                begin.next = second;
            }
        }
        return dummy.next;

    }
}
```
执行用时：5 ms, 在所有 Java 提交中击败了43.96%的用户
内存消耗：42.2 MB, 在所有 Java 提交中击败了5.88%的用户