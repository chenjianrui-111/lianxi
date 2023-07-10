package com.xiaochao.笔试;

import java.util.Scanner;

public class 二分查找 {
    static int upper_bound(int n,int v,int[] a){
        if(a[n-1] < v){
            return n + 1;
        }
        int left = 0;
        int right = n - 1;
        int mid = -1;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(v == a[mid]){
                if(mid == 0 || v!= a[mid - 1]){
                    return mid + 1;
                }else{
                    right = mid - 1;
                }
            }else if(v < a[mid]){
                if(mid == 0 || v > a[mid-1]){
                    return mid+1;
                }
                right = mid;
            }else if(v > a[mid]){
                left = mid + 1;
            }
        }
        return n + 1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int v = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n ; i++) {
            a[i] = sc.nextInt();
        }
        int i = upper_bound(n, v, a);
        System.out.println(i);
    }
}
