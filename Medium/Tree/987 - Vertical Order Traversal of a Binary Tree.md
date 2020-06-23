# 题目描述
给定二叉树，按垂序遍历返回其结点值。

对位于 (X, Y) 的每个结点而言，其左右子结点分别位于 (X-1, Y-1) 和 (X+1, Y-1)。

把一条垂线从 X = -infinity 移动到 X = +infinity ，每当该垂线与结点接触时，我们按从上到下的顺序报告结点的值（ Y 坐标递减）。

如果两个结点位置相同，则首先报告的结点值较小。

按 X 坐标顺序返回非空报告的列表。每个报告都有一个结点值列表。

示例 1：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200623231121339.png)
输入：[3,9,20,null,null,15,7]
输出：[[9],[3,15],[20],[7]]
解释： 
在不丧失其普遍性的情况下，我们可以假设根结点位于 (0, 0)：
然后，值为 9 的结点出现在 (-1, -1)；
值为 3 和 15 的两个结点分别出现在 (0, 0) 和 (0, -2)；
值为 20 的结点出现在 (1, -1)；
值为 7 的结点出现在 (2, -2)。

示例 2：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200623231145146.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3o3MTQ0MDU0ODk=,size_16,color_FFFFFF,t_70)
输入：[1,2,3,4,5,6,7]
输出：[[4],[2],[1,5,6],[3],[7]]
解释：
根据给定的方案，值为 5 和 6 的两个结点出现在同一位置。
然而，在报告 "[1,5,6]" 中，结点值 5 排在前面，因为 5 小于 6。

提示：

树的结点数介于 1 和 1000 之间。
每个结点值介于 0 和 1000 之间。

# 思路1
按题意，垂序遍历是建立在坐标的基础上的，那我们用常用的普通遍历对树进行遍历后，就能够获得所有节点的坐标信息，最后再根据坐标进行排序即可。难点在于用什么样的数据结构来存储节点的信息。

我们可以使用先序遍历找到每个节点的坐标。保持当前节点 (x, y)，移动的过程中，坐标变化为左孩子 (x-1, y+1) 或 右孩子(x+1, y+1) 。
这里的坐标通过**自定义类**来存储。这个类需要实现Comparable接口来指定排序的规则（通过 x 坐标排序，再根据 y 坐标排序，确保以正确的顺序添加到答案中）。

## 代码

```java
class Solution {
	// locations 记录每个节点的x,y坐标以及节点值
    List<Location> locations = new ArrayList<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
    	// 遍历以搜集所有节点的坐标信息
        dfs(root, 0, 0);
        // 对节点按坐标进行排序
        Collections.sort(locations);

        List<List<Integer>> ans = new ArrayList();
        ans.add(new ArrayList<Integer>());

        // 标记当前x坐标的值
        int prev = locations.get(0).x;

        for (Location loc: locations) {
            // 如果x坐标改变，那么应该生成一组新的记录
            if (loc.x != prev) {
            	// 重置标记
                prev = loc.x;
                // 结果集中添加新的list
                ans.add(new ArrayList<Integer>());
            }

            // 否则继续在同一组中添加记录
            ans.get(ans.size() - 1).add(loc.val);
        }

        return ans;
    }

    // 前序遍历记录节点坐标信息
    public void dfs(TreeNode node, int x, int y) {
        if (node != null) {
        	// 记录节点坐标和值
            locations.add(new Location(x, y, node.val));
            dfs(node.left, x-1, y+1);
            dfs(node.right, x+1, y+1);
        }
    }
}

// 自定义类存储坐标以及节点值
class Location implements Comparable<Location>{
    int x, y, val;
    Location(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    // 指定排序规则
    @Override
    public int compareTo(Location that) {
        if (this.x != that.x)
        	// 从左到右，因此首先按x排序
            return Integer.compare(this.x, that.x);
        else if (this.y != that.y)
        	// x坐标相等则按y排序，从上到下
            return Integer.compare(this.y, that.y);
        else
        	// xy坐标都相等，则先输出值小的
            return Integer.compare(this.val, that.val);
    }
}
```
执行用时：3 ms, 在所有 Java 提交中击败了97.28%的用户
内存消耗：40.3 MB, 在所有 Java 提交中击败了100.00%的用户



# 思路2
与思路1一样，只是存储信息的数据结构换成了——
TreeMap<x坐标, TreeMap<y坐标, PriorityQueue<节点值>>>
使用**TreeMap**保证x的顺序。
使用**PriorityQueue**确保同一坐标的节点值是按从小到大排序。

## 代码

```java
class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
    	// TreeMap<x坐标, TreeMap<y坐标, PriorityQueue<节点值>>>
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        List<List<Integer>> list = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> ys : map.values()) {
            list.add(new ArrayList<>());
            for (PriorityQueue<Integer> nodes : ys.values()) {
                while (!nodes.isEmpty()) {
                    list.get(list.size() - 1).add(nodes.poll());
                }
            }
        }
        return list;
    }
    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (root == null) {
            return;
        }
        if (!map.containsKey(x)) {
            map.put(x, new TreeMap<>());
        }
        if (!map.get(x).containsKey(y)) {
            map.get(x).put(y, new PriorityQueue<>());
        }
        map.get(x).get(y).offer(root.val);
        dfs(root.left, x - 1, y + 1, map);
        dfs(root.right, x + 1, y + 1, map);
    }
}
```
执行用时：4 ms, 在所有 Java 提交中击败了52.29%的用户
内存消耗：40.1 MB, 在所有 Java 提交中击败了100.00%的用户