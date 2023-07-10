package com.xiaochao.笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 老张教授开了一堂美数课！
 * 老张认为每个非负整数x都有一个美丽值b(x)。
 * 一个非负整数的美丽值定义为这个数十进制下每个数位的异或和。
 * 即，对于123来说，美丽值为1^2^3=0，对于654815424美丽值为6^5^4^8^1^5^4^2^4=9 （在C/C++中^运算符表示异或）
 * 现在老张想考考同学，对于[L,R]这个闭区间内的所有整数，美丽值恰好为t的数有多少个。
 输入：
 第一行一个正整数T，代表T个询问
 第二行T个非负整数，Li
 第三行T个非负整数，Ri
 第四行T个非负整数，ti
 输出：
 每个询问输出一个整数，每个输出用空格隔开
 例子：
 输入：
 2
 0 1
 0 10
 0 1
 输出：
 1 2
 */
public class 美术课 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] one =br.readLine().split(" ");
        String[] two =br.readLine().split(" ");
        String[] three=br.readLine().split(" ");
        int[] L = new int[n];
        int[] R = new int[n];
        int[] T = new int[n];
        for (int i = 0; i < one.length ; i++) {
            L[i] = Integer.parseInt(one[i]);
        }
        for (int i = 0; i < two.length ; i++) {
            R[i] = Integer.parseInt(two[i]);
        }
        for (int i = 0; i < three.length ; i++) {
            T[i] = Integer.parseInt(three[i]);
        }
        for (int i = 0; i < n ; i++) {
            int ans = 0;
            for (int start = L[i]; start <= R[i] ; start++) {
                int t = getBeaute(start);
                if (t == T[i])
                    ans++;
            }
            System.out.println(ans + " ");
        }

    }
    public static int getBeaute(int i){
        int temp = i % 10;
        i /= 10;
        while (i > 0){
            temp ^= i % 10;
            i /= 10;
        }
        return temp;
    }

}
