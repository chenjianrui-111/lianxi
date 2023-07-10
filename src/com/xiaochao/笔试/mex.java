package com.xiaochao.笔试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 若S表示一个非负整数集合，mex(S)的值为不属于集合S的最小非负整数。例如，mex({0, 1, 4}) = 2，mex({1, 2}) = 0。
 * 有n个互不相同的非负整数a1, a2, … an 构成了一个非负整数集合A。小美想知道若将ai (1 ≤ i ≤ n)从集合A中删除，剩下的n-1个数构成的新集合A’的mex值为多少？请输出 i 从1到 n 所有取值下新集合的mex值。
 * 输入描述
 * 第一行输入一个整数n，表示集合A的大小。
 * 第二行输入n个整数a1, a2, … an 。
 * 2 ≤ n ≤ 50000, 0 ≤ ai ≤ 109，保证ai互不相同。数字间两两有空格隔开。
 * 输出描述
 * 输出n个整数，相邻两个数之间用空格隔开。其中第i个数表示从集合A中删除ai，剩下n-1 个数构成的新集合的mex值 。
 * 样例输入
 * 4
 * 5 0 3 1
 * 样例输出
 * 2 0 2 1
 * 提示
 * 删除第1个数5，mex({0, 1, 3}) = 2；
 * 删除第2个数0，mex({5, 3, 1}) = 0；
 * 删除第3个数3，mex({5, 0, 1}) = 2；
 * 删除第4个数1，mex({5, 0, 3}) = 1。
 */
//先找出原集合中的最小值minV，若删除的数小于该最小值minV，则输出删除的数，否则输出minV. 其中找最小值的逻辑可以学习下，用一个集合s接住输入的整数，然后在整数0-n中，一定能找到最小值。
public class mex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);//排序
        int x = 0;
        if (a[a.length - 1] == n - 1) {
            x = n ;
        } else {
            for (int i = 0; i < n; i++) {
                if (a[i] != i) {
                    x = i;
                    break;
                }
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = Math.min(a[i], x);
        }
        System.out.println(Arrays.toString(ans).replaceAll("[\\[\\],]", ""));
    }
}
//n = int(input())
//        S =[int(x) for x in input().split()]
//        x = 0
//        SS = sorted(S)
//        if SS[-1] == n - 1:
//        x = n - 1
//        else:
//        for i in range(n):
//        if SS[i] != i:
//        x = i
//        break
//        ans = []
//        for i in S:
//        ans.append(min(i,x))
//        print(str(ans)[1:-1].replace(',',''))
