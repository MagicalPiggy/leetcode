# 题目描述

你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。

空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。


![.](https://img-blog.csdnimg.cn/2020061417193280.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)


# 思路
采用前序遍历，在此过程中加括号即可。麻烦之处在于有右孩子但是无左孩子的情况需要特殊处理（加上一对括号）

## 代码

```java
class Solution {
	public StringBuffer sb = new StringBuffer();
    public String tree2str(TreeNode t) {
    	if (t == null) return "";
        travel(t);
        return sb.toString();
    }

    public void travel(TreeNode t) {
    	sb.append(t.val);
    	// 叶子节点
    	if (t.left == null && t.right == null) {
            return;
        }
        if (t.left != null) {
            sb.append("(");
            travel(t.left);
            sb.append(")");
        }
        // 有右无左，需要补括号
        if (t.right != null) {
            if (t.left == null) {
                sb.append("()");
            }
            sb.append("(");
            travel(t.right);
            sb.append(")");
        }

    }
}
```