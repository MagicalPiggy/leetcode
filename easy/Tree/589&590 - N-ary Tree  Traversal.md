# leetcode-589
# 题目描述
Given an n-ary tree, return the preorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).

Follow up:

Recursive solution is trivial, could you do it iteratively?

给定一个n叉树，返回其节点值的前序遍历结果。
（递归很简单，怎么用迭代实现呢？）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601155558997.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601155623851.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
# 思路1：递归
N叉树遍历的基本框架：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601160554460.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
清晰明了，转化为代码如下：

## 代码

```java
// 递归
class Solution {
    public List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null)
            return list;
        
        list.add(root.val);
        for(Node node: root.children)
            preorder(node);
                
        return list;
    }
}
```
## 结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601160935108.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
# 思路2：迭代
类似二叉树的层序遍历，使用栈进行辅助，一个节点出栈时，把其子节点倒序入栈，接着重复此过程，可以实现先序遍历。

## 代码

```java
// 迭代
class Solution {
    public List<Integer> list = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        if (root == null)
            return list;
        
 	Stack<Node> stack = new Stack<>();
 	stack.add(root);

 	while (!stack.empty()) {
 		 root = stack.pop();
 		 list.add(root.val);
 		 // 将子节点入栈，最左边的节点在栈顶
 		 for (int i = root.children.size() - 1; i >= 0; i--)
                stack.add(root.children.get(i));
    }

    return list;
    }
}
```
## 结果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601161141118.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

# leetcode-590
N叉树的**后序**遍历，将以上代码稍作调整即可。
## 思路1：递归
## 代码

```java
// 递归
class Solution {
	public List<Integer> list = new ArrayList();
    public List<Integer> postorder(Node root) {        
        if (root == null) return list;
        for (Node child : root.children) {
        	postorder(child);
        }
        // 后序遍历，在此处处理结果
        list.add(root.val);
        return list;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601213511950.png)
## 思路2：迭代
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601205828198.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
我们可以观察结果list的**翻转**：1,5,10,9,13,4,8,12,3,7,11,14,6,2
不正是从右边开始的“前序遍历”么？？？
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601210129249.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
而在589中，我们已经知道了N叉树前序遍历的迭代解法，就是借助栈，某节点出栈时，<font color=red> 从右到左 </font>将此节点的孩子入栈。
现在变成了<font color=red> 镜像问题 </font>，那么出栈时，<font color=red> 从左到右 </font> 将节点的孩子入栈，这个过程中记录的list就是[1,5,10,9,13,4,8,12,3,7,11,14,6,2]！
最后进行一次翻转就能得到正确结果了！
是不是很神奇？
其实再理一理，N叉树的<font color=blue>后序遍历（下图的蓝色箭头） </font> = <font color=red>树从右边开始的前序遍历 (下图的红色箭头) </font>的**逆** ！
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601211300595.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)

## 代码：

```java
class Solution {
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        
        while(!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            for(Node node: root.children)
                stack.add(node);
        }
        Collections.reverse(list);
        return list;
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200601213443575.png)