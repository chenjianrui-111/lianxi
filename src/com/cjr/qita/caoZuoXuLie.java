package com.cjr.qita;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 小易有一个长度为n的整数序列,a_1,...,a_n。然后考虑在一个空序列b上进行n次以下操作:
 * 1、将a_i放入b序列的末尾
 * 2、逆置b序列
 * 小易需要你计算输出操作n次之后的b序列。
 * 输入描述:
 * 输入包括两行,第一行包括一个整数n(2 ≤ n ≤ 2*10^5),即序列的长度。
 * 第二行包括n个整数a_i(1 ≤ a_i ≤ 10^9),即序列a中的每个整数,以空格分割。
 * 输出描述:
 * 在一行中输出操作n次之后的b序列,以空格分割,行末无空格。
 * 输入
 * 4
 * 1 2 3 4
 * 输出
 * 4 2 1 3
 */
public class caoZuoXuLie {
    public static void main(String[] args) throws IOException {
        BufferedReader bfd = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bfd.readLine());
        Queue<String> queue= new LinkedList<String>();
        StringBuilder sb = new StringBuilder();
        String[] sequences = bfd.readLine().split(" ");

        int left=0;
        int index=sequences.length-1;
        int right=sequences.length-1;
        String []results=new String[sequences.length];
        while (left < right){
            results[left]=sequences[index];
            left++;
            index--;
            results[right]=sequences[index];
            right--;
            index--;
        }
        if (left == right){
            results[left]=sequences[index];
        }
        for (String s:results){
            sb.append(s+" ");
        }
        String result=sb.toString();
        System.out.println(result.substring(0,result.length()-1));
    }
}
