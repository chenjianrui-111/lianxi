package com.lizi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 构造一个长度为n的数组。
 *     1.数组最大值不超过k
 *     2.数组所有数都不同
 *     3.数组所有数之和为x
 */
//public class 构造数组 {
//    public static void main(String[] args) {
//        Scanner reader = new Scanner(System.in);
//        //        长度为n
//        int n = reader.nextInt();
//        //        最大值
//        long k = reader.nextLong();
//        //        和
//        long x = reader.nextInt();
//        if ( void trace(k, 1,n, x)) {
//            List<Integer> list = res.get(0);
//            for (int i = 0; i < list.size(); i++) {
//                System.out.print(list.get(i));
//                System.out.print(" ");
//            }
//        } else System.out.print(-1);
//
//    }
//
//    static List<List<Integer>> res = new ArrayList<>();
//    static List<Integer> list = new ArrayList<>();
//
//    public static boolean trace(long k, int index, int n, long x) {
//        if (index > k) return false;
//        if (list.size()>n) return false;
//        if (index == x && list.size() == n - 1) {
//            list.add(index);
//            res.add(new ArrayList<>(list));
//            return true;
//        }
//        for (int i = index; i <= k; i++) {
//            if (x - i < 0) {
//                break;
//            }
//            list.add(i);
//            if (trace(k, i + 1, n, x - i)) return true;
//            list.remove(list.size() - 1);
//        }
//        return false;
//
//    }
//}
