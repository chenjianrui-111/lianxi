package com.xiaochao.笔试;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //这是读取一行字符串
//        String s = sc.nextLine();
//        System.out.println(s);
//        //这是输入两个数 a b
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//        System.out.println(a + b);
        //读取[123,456]这样的
        String s1 = sc.nextLine();
        String substring = s1.substring(1, s1.length() - 1);
        String[] split = substring.split(",");
        int count = 0;
        int[] arr = new int[split.length];
        for (int i = 0; i < split.length ; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        for (int i : arr) {
            count += i;
        }
        System.out.println(count);
    }
}
