The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

 

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"



// class Solution {
//     public String countAndSay(int n) {
//     	if(n < 1)
//     		return "";
//         // String s = new String("1");
//         // String temp = new String();
//         ArrayList<Integer> list = new ArrayList<Integer>();
//         ArrayList<Integer> temp = new ArrayList<Integer>();
//         list.add(1);
//         int numOfSame = 1;
//         for(int i = 1; i <= n; i++){
//         	Collections.fill(temp,0);
//         	int j = 0;
//         	for(int j = 1; j < list.size()- 1 ; j++){
        		
//  				if(list.get(j-1) != list.get(j)){
//  					temp.add(numOfSame) ;
// 	        		temp.add(list.get(j));
// 	        		numOfSame = 1;
//  				}
//  				else
//  					numOfSame ++;
	        	
//         	}
        	
//         	list = temp;
//         }

//         return list.toString();

//     }


   
// }

// 思路1:递归
// 读懂题目后易知，每一行的结果依赖于上一行的结果，这样就可以形成递归的关系。
// 那么如何对上一行的结果进行表示呢？由例子中的规律可知，每一行连续相等的数字x被计数一次，若有n个，则在下一行写入“n个x”；继续读下一个连续相等的数字y，有m个，则在下一行写入“m个y”…此时下一行看起来是这样的“nxmy…”，这样，根据上一行的结果可以将当前行表示出来。
// 于是，代码用递归的方法从第一行推算到当前行。


class Solution{
public String countAndSay(int n) {
        if(n==1)//退出条件
            return "1";

        StringBuilder sb=new StringBuilder();

        //找到n-1的结果
        String str=countAndSay(n-1);
       
        //对n-1的结果进行表示
        char c='0';
        int count=0;
        for (int i=0;i<str.length();i++){//遍历上一个结果的字符串
            c=str.charAt(i);
            count=1;
            while ((i+1)<str.length()&&str.charAt(i+1)==c){//前面的判断防止数组下标越界
            	//若后一个字符与当前字符相等
                count++;//计数+1
                i++;//指针后移
            }
            sb.append(count+""+c);//计数+数字写入字符串缓冲区sb
        }
        return sb.toString();
    }
}


// 思路2：迭代
// 表示方法依然与思路1相同，只不过把递归换成了迭代，第一层循环完成从第2行到目标行的推移，第二层循环完成“根据上一行写出当前行”的操作。
// 这样的方法节省了堆栈的开销。

public class Solution {
    public String countAndSay(int n) {
	    	StringBuilder curr=new StringBuilder("1");
	    	StringBuilder prev;
	    	int count;
	    	char say;
	        for (int i=1;i<n;i++){
	        	prev=curr;//上一次循环的当前行->本次循环的上一行
	 	        curr=new StringBuilder(); //用来写入当前行      
	 	        count=1;
	 	        say=prev.charAt(0);//表示当前正在计数的字符
	 	        
	 	        for (int j=1,len=prev.length();j<len;j++){//遍历上一行
	 	        	if (prev.charAt(j)!=say){
	 	        		curr.append(count).append(say);
	 	        		count=1;//重置计数
	 	        		say=prev.charAt(j);//移到下一个字符开始重新计数
	 	        	}
	 	        	else count++;//否则相等，计数+1
	 	        }
	 	        curr.append(count).append(say);//最后一组元素
	        }	       	        
	        return curr.toString();
        
    }
}