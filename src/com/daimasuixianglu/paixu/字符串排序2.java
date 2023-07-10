package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 月神拿到一个新的数据集，其中每个样本都是一个字符串，样本的的后六位是纯数字，月神需要将所有样本的后六位数字提出来，转换成数字，并排序输出。
 * 注意：这里的排序并不是针对每个字符串的后六位，而是需要按数字大小顺序输出所有样本的后六位数字。
 * 月神要实现这样一个很简单的功能确没有时间，作为好朋友的你，一定能解决月神的烦恼，对吧。
 * 数据范围：字符串长度满足 1<=n<=100 ，每组测试中包含 1<=m<=100 个字符串
 * 输入描述:
 * 每个测试用例的第一行是一个正整数 M ，表示数据集的样本数目
 * 接下来输入 M 行，每行是数据集的一个样本，每个样本均是字符串，且后六位是数字字符。
 * 输出描述:
 * 对每个数据集，输出所有样本的后六位构成的数字排序后的结果（每行输出一个样本的结果）
 * 示例1
 * 输入
 * 4
 * abc123455
 * cba312456
 * boyxx213456
 * cdwxa654321
 * 输出
 * 123455
 * 213456
 * 312456
 * 654321
 */
public class 字符串排序2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] arr = new int[n];
        for(int i =0; i< n; i++) {
            String data = bf.readLine();
            arr[i] = Integer.parseInt(data.substring(data.length() - 6));
        }
        partition(arr, 0 , arr.length -1);
        for(int a : arr) {
            System.out.println(a);
        }
    }
    public static void partition(int[]arr,int L,int R){
        if (L<R){
            //去数组中任意一个数作为比较值
            swap(arr,L+(int)Math.random()*(R-L+1),R);
            int[] p=quickSort(arr,L,R);
            partition(arr, L, p[0] - 1);
            partition(arr, p[1] + 1, R);
        }
    }
    public static int[] quickSort(int[]arr,int L,int R){
        int less=L - 1;
        int more=R;
        int compareValue=arr[R];
        while (L < more){
            if (arr[L] < compareValue){
                swap(arr,++less,L++);
            }else if (arr[L] > compareValue){
                swap(arr, --more, L);
            }else {
                L++;
            }
        }
        swap(arr,more,R);
        return new int[]{less+1,more};
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
