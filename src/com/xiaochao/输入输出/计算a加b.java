package com.xiaochao.输入输出;

import java.util.Scanner;

/**
 * 输入描述:
 * 输入包括两个正整数a,b(1 <= a, b <= 1000),输入数据包括多组。
 * 输出描述:
 * 输出a+b的结果
 * 输入例子1:
 * 1 5
 * 10 20
 * 输出例子1:
 * 6
 * 30
 */
public class 计算a加b {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        while(sc.hasNextInt()){
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(a+b);
        }
    }
}

/**
 * 输入描述:
 * 输入第一行包括一个数据组数t(1 <= t <= 100)
 * 接下来每行包括两个正整数a,b(1 <= a, b <= 1000)
 * 输出描述:
 * 输出a+b的结果
 * 输入例子1:
 * 2
 * 1 5
 * 10 20
 * 输出例子1:
 * 6
 * 30
 */
class 计算a加b2{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(add(a, b));
        }
    }
    public static int add(int a, int b) {
        return a + b;
    }
}
/**
 *输入描述:
 * 输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
 * 输出描述:
 * 输出a+b的结果
 * 输入例子1:
 * 1 5
 * 10 20
 * 0 0
 * 输出例子1:
 * 6
 * 30
 */
class 计算a加b3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int a=sc.nextInt();
            int b=sc.nextInt();
            if(a==0&&b==0){
                return ;
            }
            else{
                System.out.println(a+b);
            }
        }
    }
}

/**
 * 输入数据包括多组。
 * 每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
 * 接下来n个正整数,即需要求和的每个正整数。
 * 输出描述:
 * 每组数据输出求和的结果
 * 输入例子1:
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 * 0
 * 输出例子1:
 * 10
 * 15
 */
class 计算a加b4{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while(true){
            n = sc.nextInt();
            if(n == 0)
                return;
            int sum = 0;
            for(int i = 0 ; i < n ; i++){
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}

/**
 * 数据范围：数据组数满足  ，每组数据中的整数个数满足  ，每个数据大小满足
 * 输入描述:
 * 输入的第一行包括一个正整数t(1 <= t <= 100), 表示数据组数。
 * 接下来t行, 每行一组数据。
 * 每行的第一个整数为整数的个数n(1 <= n <= 100)。
 * 接下来n个正整数, 即需要求和的每个正整数。
 * 输出描述:
 * 每组数据输出求和的结果
 * 输入例子1:
 * 2
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 * 输出例子1:
 * 10
 * 15
 */
class 计算a加b5{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int nums = sc.nextInt();
            for (int j = 0; j < nums ; j++) {
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}

class 计算a加b6{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String[] str = in.nextLine().split(" ");
            int sum = 0;
            for(int i = 1; i <= Integer.parseInt(str[0]); i++) {
                // 注意是小于等于号！
                sum += Integer.parseInt(str[i]);
            }
            System.out.println(sum);
        }
    }
}

/**
 * 输入描述:
 * 输入数据有多组, 每行表示一组输入数据。
 * 每行不定有n个整数，空格隔开。(1 <= n <= 100)。
 * 输出描述:
 * 每组数据输出求和的结果
 * 输入例子1:
 * 1 2 3
 * 4 5
 * 0 0 0 0 0
 * 输出例子1:
 * 6
 * 9
 * 0
 */
class 计算a加b7{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String[] s = in.nextLine().split(" ");
            int sum = 0;
            for(int i = 0; i < s.length; i++) {
                sum += Integer.parseInt(s[i]);
            }
            System.out.println(sum);
        }
    }
}
