package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 排序 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] str2 = br.readLine().split(" ");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(str2[i]);
        }
        quickSort(arr, 0, n-1);
        for(int i = 0; i < n; i++){
            System.out.print(arr[i] + " ");
        }

    }

    public static void quickSort(int[] arr, int L, int R){
        if(L < R){
            int target = L + (int)(Math.random()*(R-L+1));
            swap(arr, target, R);
            int[] index = partition(arr, L, R);

            quickSort(arr, L, index[0]-1);
            quickSort(arr, index[1]+1, R);
        }
    }

    public static int[] partition(int[] arr, int L, int R){
        int less = L-1;
        int more = R; // 对除了最后一位的其他数进行排序
        int target = arr[R];
        while(L < more){
            if(arr[L] < target){
                swap(arr, L++, ++less);
            }else if(arr[L] > target){
                swap(arr, L, --more);
            }else{
                L++;
            }
        }
        swap(arr, R, more);
        return new int[] {less+1, more}; //返回时记得带上最后一个数
    }

    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
