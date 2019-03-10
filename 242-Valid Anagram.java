// Given two strings s and t , write a function to determine if t is an anagram of s.

// Example 1:

// Input: s = "anagram", t = "nagaram"
// Output: true


// Example 2:

// Input: s = "rat", t = "car"
// Output: false


// Note:
// You may assume the string contains only lowercase alphabets.

// Follow up:
// What if the inputs contain unicode characters? How would you adapt your solution to such case?

// 思路1：排序
// 将两个字符串分别转换为字符数组并调用Arrays.sort()方法对字符数组进行排序，并判断排序后的数组是否相同
// 时间复杂度：涉及到排序，为O(nlgn)

class Solution {
	public boolean isAnagram(String s, String t) {
	    if (s.length() != t.length()) {
	        return false;
	    }
	    char[] str1 = s.toCharArray();
	    char[] str2 = t.toCharArray();
	    Arrays.sort(str1);
	    Arrays.sort(str2);
	    return Arrays.equals(str1, str2);
	}
}


// 思路2：hashtable
// 用重复检测的方法来考虑：
// 用一个数组来纪录字符串中各个字母出现的次数，并且为了节省空间可以只用一个数组完成，遍历一趟，s中的某字母出现一次就让对应位置计数+1，反之，t中字母出现时使计数-1。若t是s的"anagram"，那么最后这个counter数组中的所有元素都应该是0。
// unicode的情况把数组换成hashtable。

// 时间复杂度O(n)

class Solution {
	public boolean isAnagram(String s, String t) {
	    if (s.length() != t.length()) {
	        return false;
	    }
	    int[] counter = new int[26];
	    for(int i = 0; i < s.length(); i ++){
	    	counter[s.charAt(i) - 'a']++;
	    	counter[t.charAt(i) - 'a']--;

	    }
	    for(int count : counter ){
	    	if(count != 0){
	    		return false;
	    	}

	    }
	    return true;
	}
}


