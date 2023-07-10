package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 字符串第k位
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 *         小美和小团拥有一个字符串生成机器，每次将字符串s丢入这个机器中，这个机器将会弹出s+r(s)+”wow”，其中，加号表示字符串的拼接，r(s)表示将字符串s逆序。
 *         例如当s=”abc”时，得到的字符串为s+r(s)+”wow”=”abccbawow”。
 *         当s=”MeiTuan”时，得到的字符串为”MeiTuannauTieMwow”。
 *         小美和小团想知道，如果有一个初始字符串”MeiTuan”，然后将这个字符串丢入机器得到一个新串，然后不断地将新串再次丢入机器中，循环往复得到的无限长的字符串的第k位字符（k从1开始）是什么？（根据生成规则可知，每次丢入机器的串都会是生成的结果串的一个前缀）
 * 输入描述
 * 第一行是一个整数T，表示有T个询问。
 * 接下来T行每行一个整数k，表示询问无限长字符串中第k个字符是多少。
 * 1<=T<=10,   1<=k<=10^18
 * 输出描述
 * T行，第 i 行包含一个字符，表示第i个询问的答案。
 * 样例输入
 * 3
 * 31
 * 51
 * 67
 * 样例输出
 * T
 * T
 * n
 */
public class 字符串第k位 {
//    public static void main(String[] args) {
//        Scanner sc =new Scanner(System.in);
//        StringBuilder sb1 = new StringBuilder();
//        int n =sc.nextInt();
//        int[] arr = new int[n];
//        for (int i = 0; i <n ; i++) {
//            arr[i] = sc.nextInt();
//        }
//        for (int i = 0; i <=n ; i++) {
//            String transform = transform();
//        }
//       int k = transform().length();
//        for (int i = 0; i <arr.length ;) {
//            for (int j = 0;j< k;j++){
//                if (j == arr[i]){
//                    i++;
//                    System.out.println(transform().indexOf(j));
//                }
//            }
//        }
//    }
//    public static String transform(){
//        StringBuilder sb = new StringBuilder();
//        String str = "MeiTuan";
//        String s = buffer(str);
//        StringBuilder sbs = sb.append(str).append(s).append("wow");
//        str = String.valueOf(sbs);
//        return sbs.toString();
//    }
//    public static String buffer(String s){
//        int length=s.length();
//        StringBuffer sb=new StringBuffer();
//        for(int i=s.length()-1;i>=0;i--){
//            sb.append(s.charAt(i));
//        }
//        return sb.toString();
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("MeiTuannauTieMWoWWoW".charAt((i - 1)%20)); //发现一直是这20个字符在循环
    }
}
