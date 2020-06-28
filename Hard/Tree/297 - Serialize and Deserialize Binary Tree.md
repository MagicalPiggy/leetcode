# 题目描述
序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，<font color=red> 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构</font>。

示例: 

你可以将以下二叉树：

```java
    1
   / \
  2   3
     / \
    4   5a
```

序列化为 "[1,2,3,null,null,4,5]"

说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。

# 解法

采用先序遍历，对二叉树进行序列化。
每到达一个节点，将节点的值+逗号添加到**StringBuilder**中，遇到空节点则添加null。最后转换为字符串返回。
避免使用字符串拼接，性能太差。
例子中的树序列化后的结果为："1,2,null,null,3,4,null,null,5,null,null,"

```java
	public String serialize(TreeNode root) {
		StringBuilder res = serializeHelper(root, new StringBuilder());
        return res.toString();
    }

    public StringBuilder serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
        } else {
        	sb.append(root.val);  
        	sb.append(",");         
            serializeHelper(root.left, sb);
            serializeHelper(root.right, sb);
        }
        return sb;
    }
```

反序列化时，先将字符串根据逗号分隔为字符串数组，进而转化为list，然后从左向右遍历这个序列，根据序列的首位元素构造节点，每次处理完，删除序列中的该元素：
- 如果当前的元素为 None，则当前为空树
- 否则先构造这棵树的左子树，再构造它的右子树。
```java
public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return deserializeHelper(data_list);
}

public TreeNode deserializeHelper(List<String> list) {
    if (list.get(0).equals("null")) {
        list.remove(0);
        return null;
    }

    TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
    list.remove(0);
    root.left = deserializeHelper(list);
    root.right = deserializeHelper(list);

    return root;
}
```
整体提交结果：
执行用时：12 ms, 在所有 Java 提交中击败了86.23%的用户
内存消耗：41.1 MB, 在所有 Java 提交中击败了28.57%的用户