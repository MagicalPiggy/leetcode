// Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

// Examples:

// s = "leetcode"
// return 0.

// s = "loveleetcode",
// return 2.
// Note: You may assume the string contain only lowercase letters.

// class Solution {
//     public int firstUniqChar(String s) {
//         Set<Char> set = new HashSet<>(s.lenght());
//         int a = s.lenght();//用来记录第一次出现重复元素的位置
//         for(int i = 0; i <s.lenght(); i++ ){
//         	if(set.contains(s.charAt(i)) &&  (s.indexOf(s.charAt(i)) < a))
        		
//         		a = s.indexOf(s.charAt(i));
//         	if(!set.contains(s.charAt(i)))
        		
//         		set.add(s.charAt(i));
//         }
//         if(a == s.lenght())
//         	return -1;
//         else
//         	return a ;

//     }
// }


class Solution {
public int firstUniqChar(String s) {
        
        Map<Character, Integer> map = new HashMap<>();
        int res = -1;//默认情况：所有字符都是重复的，返回-1
        
         for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 0);//标记置0表示还未发现重复元素
            } else {//检测到重复
                map.put(s.charAt(i), 1);//把标记置为1，表示这是重复元素
            }
        }

        
        for (int i = 0; i < s.length(); i++) {//统计所有元素出现的次数
            if (map.get(s.charAt(i)) == 0) { //只要发现不重复元素，就记录位置并退出循环
                    res = i;
                    break;
                }
            }
        return res;
    }
}