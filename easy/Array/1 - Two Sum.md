# 题目描述
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。


示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]



# 思路
第一感可以用暴力搜索，但是这样的时间复杂度为O(n^2)，可以稍加改进，遍历时将访问过的数字记录下来，在访问其他元素时比对这个记录，是否记录中有元素能与当前元素的和符合目标。
时间复杂度O(n)

# 代码1
最初写的代码是这样的：

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
    	int[] res = new int[2];
        List<Integer> list = new ArrayList<Integer>(nums.length);
        for(int i = 0;i < nums.length ; i++){
        	if(list.contains(target-nums[i])){
        		res[0] = list.indexOf(target-nums[i]);
        		res[1] = i;
        		
        	}
        	
        	list.add(nums[i]);
        }
        return res;
    }
}
```
## 提交结果
Runtime: 64 ms, faster than 6.46% of Java online submissions for Two Sum.
Memory Usage: 38.5 MB, less than 48.64% of Java online submissions for Two Sum.
运行时间不是分理想，明明是O(n)的时间复杂度，为什么会这么慢呢？
可能list.contains或list.indexOf在效率上不是那么高，下面进行改进，改用HashMap来保存。

# 代码2

```java
class Solution {
public int[] twoSum(int[] nums, int target) {
	int[] result = new int[2];
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
             result[0] = map.get(complement);
             result[1] = i;
        }
        map.put(nums[i], i);
    }
    //throw new IllegalArgumentException("No two sum solution");
    return result;
}
}
```

## 提交结果
执行用时：2 ms, 在所有 Java 提交中击败了99.61%的用户
内存消耗：40.1 MB, 在所有 Java 提交中击败了5.06%的用户

可以看到时间快了不少，这说明HashMap的containsKey方法在效率上要高？

# 小结:
**ArrayList**使用的是数组来存储元素，而HashMap使用**哈希表**来存储元素。在上面两份代码中，效率拉开差距的是在ArrayList的**contains**和HashMap的**containsKey**方法上。

ArrayList的contains方法是通过调用自己的index of方法来判断的，下面上源码：

```java
public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

```
可以看到，indexOf方法把整个数组顺序遍历了一遍，这种查找方法无疑是效率最低的。

在HashMap，key被存储到hash表中，查找时是在hash表上进行查找，这样查找的时间复杂度为**O(1)**，是非常高效的查找方法。
这部分的源代码如下：

```java
public boolean containsKey(Object key) {
        return getNode(hash(key), key) != null;
    }
```

```java

final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node
                ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                if (first instanceof TreeNode)
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

```
为什么HashMap查找的效率如此之高？
看到一篇文章讲得很简明：[java中hashmap容器实现查找O(1)时间复杂度的思考](https://blog.csdn.net/u014633283/article/details/48549155)

总之，如果要用**contains**方法，用HashMap来代替要远远快于ArrayList，当然ArrayList的长处在于，需要进行遍历时它能派上用场。