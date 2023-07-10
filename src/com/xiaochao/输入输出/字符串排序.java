package com.xiaochao.输入输出;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 输入描述:
 * 输入有两行，第一行n
 * 第二行是n个字符串，字符串之间用空格隔开
 * 输出描述:
 * 输出一行排序后的字符串，空格隔开，无结尾空格
 * 输入例子1:
 * 5
 * c d a bb e
 * 输出例子1:
 * a bb c d e
 */
public class 字符串排序 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int a= Integer.parseInt(sc.nextLine());
        String b[]=sc.nextLine().split(" ");
        String temp="";
        for(int i=0;i<a;i++){
            for(int j=i+1;j<a;j++){
                if(b[i].charAt(0)>b[j].charAt(0)){
                    temp=b[i];
                    b[i]=b[j];
                    b[j]=temp;
                }
            }

        }
        for(int i=0;i<a;i++){
            System.out.print(b[i]+" ");
        }
    }
}

/**
 * 输入描述:
 * 多个测试用例，每个测试用例一行。
 * 每行通过空格隔开，有n个字符，n＜100
 * 输出描述:
 * 对于每组测试用例，输出一行排序过的字符串，每个字符串通过空格隔开
 * 输入例子1:
 * a c bb
 * f dddd
 * nowcoder
 * 输出例子1:
 * a bb c
 * dddd f
 * nowcoder
 */
class 字符串排序2{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null){
            String[] data = line.split(" ");
            Arrays.sort(data);
            for(int i = 0; i < data.length-1; i++){
                System.out.print(data[i] + " ");
            }
            System.out.println(data[data.length-1]);
        }
    }
}

/**
 * 输入描述:
 * 多个测试用例，每个测试用例一行。
 * 每行通过,隔开，有n个字符，n＜100
 * 输出描述:
 * 对于每组用例输出一行排序后的字符串，用','隔开，无结尾空格
 * 输入例子1:
 * a,c,bb
 * f,dddd
 * nowcoder
 * 输出例子1:
 * a,bb,c
 * dddd,f
 * nowcoder
 */
class 字符串排序3{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = br.readLine()) != null){
            String[] data = line.split(",");
            Arrays.sort(data);
            for(int i = 0; i < data.length - 1; ++i)
                System.out.print(data[i] + ",");
            System.out.println(data[data.length - 1]);
        }
    }
}
