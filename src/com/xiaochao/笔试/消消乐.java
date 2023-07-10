package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.Scanner;
//删除相邻重复，删除次数为奇数就赢，偶数就输
public class 消消乐 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String sb = sc.next();
            list.add(sb);
        }
        for (int i = 0; i < n; i++) {
            int time = find(list.get(i));
            if (time % 2 == 0) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
        }
    }

    public static int find(String sb) {
        int pre = 0;
        int time = 0;
        while (true) {
            pre = time;
            for (int i = 1; i < sb.length(); i++) {
                if (sb.charAt(i) == sb.charAt(i - 1)) {
                    time++;
                    sb = sb.substring(0, i - 1) + sb.substring(i + 1);
                }
            }
            if (pre == time) {
                break;
            }
        }
        return time;
    }
}

