# 题目描述

[原题链接](https://www.nowcoder.com/test/question/e9a4919b8848451d9aff81e3cdd133b1?pid=12398771&tid=38805937)

2的N次方

对于一个整数N（512 <= N <= 1024），计算2的N次方并在屏幕显示十进制结果。

 

**输入描述:**

```
输入一个整数N（512 <= N <= 1024）
```

**输出描述:**

```
2的N次方的十进制结果
```

**输入例子1:**

```
512
```

**输出例子1:**

```
13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084096
```

# 思路

考察大数乘法。大数一般联想到字符串。但是字符串不方便修改值，因此采用StringBuilder来表示大数。

循环n次进行X2操作，每次操作需要将StringBuilder中的字符依次取出X2并处理进位。

时间复杂度O(n^2)

## 代码

```java
public class PowOf2 {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         System.out.println(pow2(n));
    }

    private static String pow2(int n) {
        StringBuilder res = new StringBuilder( "1" );
        // 重复N次
        for (int i = 0; i < n; i++) {
            // 进位标志，每轮清零
            int temp = 0;
            // result中的字符，从前往后逐位*2
            for (int j = res.length() -1; j >= 0; j--) {
                // 乘法运算,需要加上进位
                temp = ((res.charAt(j) - '0') <<1) + temp / 10 ;
                // 替换此位结果
                res.setCharAt(j, (char)(temp%10 + '0')) ;
            }
            // 产生进位则需添加新的数字
            if (temp/10 >= 1)
                res.insert(0,'1') ;
        }

        return res.toString();
    }
}
```

