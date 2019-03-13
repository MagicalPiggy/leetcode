// Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

// Symbol       Value
// I             1
// V             5
// X             10
// L             50
// C             100
// D             500
// M             1000
// For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

// Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

// I can be placed before V (5) and X (10) to make 4 and 9. 
// X can be placed before L (50) and C (100) to make 40 and 90. 
// C can be placed before D (500) and M (1000) to make 400 and 900.
// Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

// Example 1:

// Input: "III"
// Output: 3
// Example 2:

// Input: "IV"
// Output: 4
// Example 3:

// Input: "IX"
// Output: 9
// Example 4:

// Input: "LVIII"
// Output: 58
// Explanation: L = 50, V= 5, III = 3.
// Example 5:

// Input: "MCMXCIV"
// Output: 1994
// Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

//思路
// 首先将String转为字符数组a
// 遍历a——

// 考虑特殊组合的情况，若以 I,X,C 开头，则判断后一位是否能与该位组成特殊组合，若确实是特殊组合，使最后结果sum
// 加上对应的值，并让i往后移动一位：
// IV 4  IX 9
// XL 40 XC 90
// CD 400 CM 900


// 否则直接将对应的数值加到结果里
// 注意数组越界，所以要判断i是否遍历到了最后一位

// 代码1
class Solution {
    public int romanToInt(String s) {
        int sum = 0;
        char a[] = s.toCharArray();//转换成字符数组
        for(int i = 0;i < a.length; i ++){
        	if(a[i] == 'I' && i != a.length - 1){
        		if(a[i+1] == 'V'){
        			sum += 4;
        			i ++;
        			continue;
        		}
        		if(a[i+1] == 'X'){
        			sum +=9;
        			i ++;
        			continue;
        		}
        		else{
        			sum += 1;
        			
        		}
        	}

        	if(a[i] == 'I' && i == a.length - 1){
        		sum += 1;
        		
        	}

        	if(a[i] == 'X' && i != a.length - 1){
        		if(a[i+1] == 'L'){
        			sum += 40;
        			i ++;
        			continue;
        		}
        		if(a[i+1] == 'C'){
        			sum +=90;
        			i ++;
        			continue;
        		}
        		else{
        			sum += 10;
        			
        		}
        	}

        	if(a[i] == 'X' && i == a.length - 1){
        		sum += 10;
        		
        	}

        	if(a[i] == 'C' && i != a.length - 1){
        		if(a[i+1] == 'D'){
        			sum += 400;
        			i ++;
        			continue;
        		}

        		if(a[i+1] == 'M'){
        			sum +=900;
        			i ++;
        			continue;
        		}
        		else{
        			sum += 100;
        			
        		}
        	}

        	if(a[i] == 'C' && i == a.length - 1){
        		sum += 100;
        		
        	}


        	if(a[i] == 'V') sum += 5;
        	if(a[i] == 'L') sum += 50;
        	if(a[i] == 'D') sum += 500;
        	if(a[i] == 'M') sum += 1000;

        }

       	return sum;
    	}
     
	}

// 代码2
class Solution {
    public int romanToInt(String s) {
		   if (s == null || s.length() == 0) {
            return 0;
        }
         int sum=0,i;
        char chs[]=s.toCharArray();
         for(i=0;i<chs.length;i++)
         {
             if(chs[i]=='I') sum+=1;
             if(chs[i]=='V')
             {
                if(i!=0 && chs[i-1]=='I') sum+=4-1;//还要把上次累加上的减去
                else sum+=5;
             }
             if(chs[i]=='X')
             {
                 if(i!=0 && chs[i-1]=='I') sum+=9-1;
                 else sum+=10;
             }
             if(chs[i]=='L')
             {
                 if(i!=0 &&chs[i-1]=='X') sum+=40-10;
                 else sum+=50;
             }
             if(chs[i]=='C')
             {
                 if(i!=0 && chs[i-1]=='X') sum+=90-10;
                 else sum+=100;
             }
             if(chs[i]=='D')
             {
                 if(i!=0 && chs[i-1]=='C') sum+=400-100;
             else sum+=500;
             }
             if(chs[i]=='M') 
             {
                if(i!=0 && chs[i-1]=='C') sum+=900-100;
                 else sum+=1000;
             }
         }
         return sum;
    }
}



//代码3:map实现
class Solution {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int sum = 0;
        char a[]=s.toCharArray();
        for (int i = 0; i < a.length ; i++) {
            if (i != a.length-1 && map.get(a[i]) < map.get(a[i+1])) {
                sum -= map.get(a[i]);
            }
            else {
                sum += map.get(a[i]);
            }
        }
        return sum;
        
    }
}


