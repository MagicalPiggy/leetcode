# 题目描述
序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。

设计一个算法来序列化和反序列化二叉搜索树。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。

编码的字符串应尽可能紧凑。

注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。

# 思路
参考[297 - 二叉树的序列化与反序列化](https://github.com/MagicalPiggy/leetcode/blob/master/Hard/Tree/297%20-%20Serialize%20and%20Deserialize%20Binary%20Tree.md)。
该题的解法是对于所有二叉树一般化的解法，可以直接套用在本题，因为二叉搜索树是一种特殊的二叉树。

那么如何利用二叉搜索树的性质，对一般化的解法进行优化呢？

在一般化的解法中，序列化时，当遇到空节点时，我们必须在字符串中添加"null"或其他字符，来标记这个位置应该是空节点，以便在反序列化时构造正确的树。

而对于二叉搜索树，可以不需要"null"来标记空节点，那么在构造树时，利用其性质——左孩子的值小于父节点，右孩子的值大于父节点——来判断某个位置是否应该为空。

反序列化时，将字符串转为队列，按照先序构造树，每次访问到一个位置，传入一对上下界，用于判断队列首位的值是否在这个范围内——
- 若在范围内，说明这个值应该处于树的这个位置，那么出队，并递归构造左右子树。构造左子树时，将此节点值作为上界（左孩子的值必须小于此节点）；同理，构造右子树时，将此节点值作为下界。
- 若不在范围内，说明队首的值不应该在此位置，结束递归。

## 代码

```java
public class Codec {

    // 将BST序列化为字符串
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    
    // 前序遍历，遇到空节点不添加null
    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // 将字符串反序列化为BST
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        // 将字符串转为队列
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public TreeNode deserialize(Queue<String> q, int lower, int upper) {
        // 队列为空，说明树已经构造完毕，递归结束
        if (q.isEmpty()) return null;
        
        // 取队首元素的值
        String s = q.peek();
        int val = Integer.parseInt(s);

        // 如果值不在范围中，说明这个位置应该为空，结束本层递归
        if (val < lower || val > upper) return null;
        
        // 如果值在范围中，将其出队，用值构造树
        q.poll();
        TreeNode root = new TreeNode(val);

        // 将root值作为上界，构造左子树
        root.left = deserialize(q, lower, val);
        // 将root值作为下界，构造右子树
        root.right = deserialize(q, val, upper);
        
        return root;
    }
}
```

执行用时：6 ms, 在所有 Java 提交中击败了93.02%的用户
内存消耗：40.9 MB, 在所有 Java 提交中击败了100.00%的用户