class Solution {
        public int strStr(String s, String t) {
        if (t.isEmpty()) return 0; // 边界条件: "",""=>0  "a",""=>0
        for (int i = 0; i <= s.length() - t.length(); i++) { 
            for (int j = 0; j < t.length() && s.charAt(i + j) == t.charAt(j); j++)  
                if (j == t.length() - 1) //匹配 结束再返回s中的匹配起点 
                	return i;
        }
       // 暴力搜索无果则返回-1
        return -1;
    }
}