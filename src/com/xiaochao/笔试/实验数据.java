package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 小昱做了很久的实验得到了一个用正整数表示的实验数据，并记录在了纸上。但是由于做完实验太过激动，他一不小心把墨水打翻溅在了纸上，
 * 导致数据中一些位置上的数字看不清楚。他仍记得这个数据有以下三个特征：
 * 1. 这个数是正整数，且没有前导零（即数的最高位不是0）
 * 2. 这个数任意两个相邻数位的数字不同
 * 3. 这个数可以被3整除
 * 他现在很关心在满足以上特征的条件下，这个数字最小为多少。
 * 输入描述
 * 输入一个由数字0-9和‘?’构成的字符串。若输入的第i个字符为问号，则表示数据从高位往低位数的第i位被墨水遮盖，
 * 不能确定是哪个数字；否则，表示这一位未被墨水遮盖，是一个确定的数。
 * 输出描述
 * 输出一个正整数，表示实验数据最小可能是多少。
 * 样例输入
 * ?12?0?9??
 * 样例输出
 * 212101902
 * 提示
 * 输入的字符串长度不超过100000，且至少为1。
 * 所有数据均保证合法，保证存在合法解，且至少含有1个‘?’。
 * 最高位为‘?’表示最高位被遮挡无法确定。因为第一条特征限制，最高位不能为0。因为第二位为1，根据第二条限制，最高位不能为1。
 * 所以最高位只能是2到9中的任意一个数字，当最高位为2时，实验数据会最小。第4、6、8、9位数字也被墨水遮挡，当第4、6位为数字1，第8位为数字为0，
 * 第9位为数字2时满足小昱记忆中数据的特征，且是可能出现的最小值。
 */
public class 实验数据 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        char[] ans = new char[n];
        int prefixSumMod3 = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '?') {
                if (prefixSumMod3 == 0) {
                    ans[i] = '1';
                } else if (prefixSumMod3 == 1) {
                    ans[i] = '2';
                } else {
                    ans[i] = '1';
                    prefixSumMod3 = 2;
                }
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(j) != '?') {
                        break;
                    }
                    if (prefixSumMod3 == 0) {
                        ans[j] = '1';
                    } else if (prefixSumMod3 == 1) {
                        ans[j] = '2';
                    } else {
                        ans[j] = '1';
                        prefixSumMod3 = 2;
                    }
                }
                continue;
            }
            ans[i] = s.charAt(i);
            int d = ans[i] - '0';
            prefixSumMod3 = (prefixSumMod3 + d) % 3;
            if (i > 0) {
                int prevD = ans[i-1] - '0';
                if (prevD == d) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        if (prefixSumMod3 != 0) {
            System.out.println(-1);
            return;
        }
        System.out.println(new String(ans));
    }
}
//#include <bits/stdc++.h>
//        #define ll long long
//        const int maxx = 3e5 + 5;
//        using namespace std;
//        int n, m, k;
//        char a[maxx];
//        ll sum[maxx];
//        int main()
//        {
//        scanf("%s", a + 1);
//        int n = strlen(a + 1);
//        ll now = 0;
//        int index = 0;
//        for (int i = n; i >= 1; i--) {
//        if (a[i] == '?') {
//        index = i;
//        break;
//        }
//        }
//        if (n == 1 && a[1] == '?') {
//        a[1] = '0';
//        }
//        if (a[1] == '?') {
//        if (a[2] == '1') a[1] = '2';
//        else a[1] = '1';
//        }
//        for (int i = index + 1; i <= n; i++) {
//        now = (now + (a[i] - '0')) % 3;
//        }
//
//        for (int i = 1; i <= n; i++) {
//        if (a[i] == '?') {
//        if (i == index) {
//        for (int j = 0; j <= 9; j++) {
//        if ((now + j) % 3 == 0 && j != (a[i - 1] - '0') && j != (a[i + 1] - '0')) {
//        a[i] = '0' + j;
//        //printf("%d %c\n", j, a[i - 1]);
//        j = 100;
//        }
//        }
//        }
//        else {
//        for (int j = 0; j <= 9; j++) {
//        if (j != (a[i - 1] - '0') && j != (a[i + 1] - '0') ) {
//        a[i] = '0' + j;
//        //printf("%d %c\n", j, a[i - 1]);
//        now = (now + j) % 3;
//        j = 100;
//        }
//        }
//        }
//
//        }
//        else {
//        int p = (a[i] - '0');
//        now = (now + p) % 3;
//        }
//        }
//        printf("%s\n", a + 1);
//        return 0;
//        }
