package com.daimasuixianglu.beibao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 一个栈依次压入1,2,3,4,5，那么从栈顶到栈底分别为5,4,3,2,1。将这个栈转置后，从栈顶到栈底为1,2,3,4,5，也就是实现栈中元素的逆序，但是只能用递归函数来实现，不能用其他数据结构。
 * 输入描述:
 * 输入数据第一行一个整数N为栈中元素的个数。
 * 接下来一行N个整数X_i表示一个栈依次压入的每个元素。
 * 输出描述:
 * 输出一行表示栈中元素逆序后的栈顶到栈底的每个元素
 * 示例1
 * 输入
 * 5
 * 1 2 3 4 5
 * 输出
 * 1 2 3 4 5
 */
public class 用递归函数和栈逆序一个栈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();
        String[] str=br.readLine().split(" ");
        for (int i = 0; i <str.length ; i++) {
            sb.append(str[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
        br.close();
    }
}
