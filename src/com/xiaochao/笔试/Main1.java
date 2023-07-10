package com.xiaochao.笔试;
import java.util.*;

/**
 * 组题
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 * 有三个题库A、B、C，每个题库均有n道题目，且题目都是从1到n进行编号。每个题目都有一个难度值，题库A中第i个题目的难度为ai，题库B中第i个题目的难度为bi，题库C中第i个题目的难度为ci。
 * 小美准备组合出一套试题，试题共有三道题，第一题来自题库A，第二题来自题库B，第三题来自题库C。试题要求题目难度递增，且梯度不能过大。具体地说，第二题的难度必须大于第一题的难度，但不能大于第一题难度的两倍；第三题的难度必须大于第二题的难度，但不能大于第二题难度的两倍。小美想知道在满足上述要求下，有多少种不同的题目组合？（三道题目中只要存在一道题目不同，则两个题目组合就视为不同）
 * 输入描述
 * 第一行一个正整数n, 表示每个题库的题目数量。
 * 第二行为n个正整数a1, a2,...... an，其中ai表示题库A中第i个题目的难度值。
 * 第三行为n个正整数b1, b2,...... bn，其中bi表示题库B中第i个题目的难度值。
 * 第四行为n个正整数c1, c2,...... cn，其中ci表示题库C中第i个题目的难度值。
 * 1 ≤ n ≤ 20000, 1 ≤ ai, bi, ci ≤ 109。
 * 输出描述
 * 输出一个整数，表示满足要求的题目组合种类数。
 * 样例输入
 * 3
 * 2 7 2
 * 1 5 3
 * 4 6 3
 * 样例输出
 * 4
 * 提示
 * 满足条件的4种组合方式：
 * 1.选择题库A的第一题，题库B的第三题，题库C的第一题，三道题的难度依次为2、3、4。
 * 2.选择题库A的第一题，题库B的第三题，题库C的第二题，三道题的难度依次为2、3、6。
 * 3.选择题库A的第三题，题库B的第三题，题库C的第一题，三道题的难度依次为2、3、4。
 * 4.选择题库A的第三题，题库B的第三题，题库C的第二题，三道题的难度依次为2、3、6。
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        int res = 0;
        int num1 = 1;
        int num2 = 1;
        int num3 = 1;
        for (int i = 0; i < a.length; i++) {
            if (i < a.length - 1) {
                if (a[i] == a[i + 1]) {
                    num1++;
                    continue;
                }
            }
            for (int j = 0; j < b.length; j++) {
                if (b[j] > a[i] && b[j] <= 2 * a[i]) {
                    if (j < b.length - 1) {
                        if (b[j] == b[j + 1]) {
                            num2++;
                            continue;
                        }
                    }
                    for (int k = 0; k < c.length; k++) {
                        if (c[k] > b[j] && c[k] <= 2 * b[j]) {
                            if (k < c.length - 1) {
                                if (c[k] == c[k + 1]) {
                                    num3++;
                                    continue;
                                }
                            }
                            res += num1 * num2 * num3;
                            num3 = 1;
                        } else {
                            continue;
                        }
                    }
                    num2 = 1;
                } else {
                    continue;
                }
            }
            num1 = 1;
        }
        System.out.println(res);
    }
}

