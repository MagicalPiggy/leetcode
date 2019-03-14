Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:
Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],  	
 [1,4,6,4,1]
]




// public class Solution {
// 	public List<List<Integer>> generate(int numRows)
// 	{
// 		List<List<Integer>> allrows = new ArrayList<List<Integer>>();//定义二维数组
// 		ArrayList<Integer> row = new ArrayList<Integer>();//定义每一行为一个动态数组
// 		for(int i=0;i<numRows;i++)//逐行操作
// 		{
// 			row.add(0, 1);//每一行第一个元素一定是1
// 			for(int j=1;j<row.size()-1;j++)
// 				row.set(j, row.get(j)+row.get(j+1));
// 			allrows.add(new ArrayList<Integer>(row));
// 		}
// 		return allrows;
		
// 	}
// }


// 根据题目的意思，逐行操作（第一行只有1），从第二行开始，每一行的首尾元素一定是1，其他位置上的元素是：上一行中对应位置的+对应前一个位置的元素之和，因此，每一行需要从上一行中获得相应的元素。
// 最后返回的是二维数组List<List>，
// 而每一行我们用ArrayList集合来保存（因为需要取角标）。
// 在对每一行操作前，用get()方法获取上一行的ArrayList。
// 同样，在取上一行的两个元素相加时用的也是get()方法。
// 时间复杂度：O(n^2)


class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // 第一种情况，传入行数为零，直接返回一个空数组
        if (numRows == 0) {
            return triangle;
        }

        // 否则，第一行的数组只有一个1元素

        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);



        for (int rowNum = 1; rowNum < numRows; rowNum++) {//从第二行开始逐行操作
            List<Integer> row = new ArrayList<>();//当前行对应的数组
            List<Integer> prevRow = triangle.get(rowNum-1);//取出上一行的数组，注意，第一行数组在triangle中对应的下标是0

            // 每一行的第一个元素一定是1
            row.add(1);

            // 从每一行的第2个元素开始，直到倒数第二个
            // 其中每个元素等于上一行中，对应下标的元素+对应下标-1的元素

            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));//关键语句
            }

            // 每一行的最后一个元素一定是1
            row.add(1);

            //最后把整行添加到二维数组中
            triangle.add(row);
        }

        return triangle;
    }
}

