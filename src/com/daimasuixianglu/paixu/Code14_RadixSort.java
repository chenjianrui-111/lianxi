package com.daimasuixianglu.paixu;

//public class Code14_RadixSort {
//
//    public static void radixSort(int[] arr){
//        if (arr == null || arr.length < 2){
//            return;
//        }
//        radixSort(arr,0,arr.length-1,maxbits(arr));
//    }
//
//    public static int maxbits(int[] arr){
//        int max=Integer.MIN_VALUE;
//        for (int i = 0; i <arr.length ; i++) {
//            max=Math.max(max,arr[i]);
//        }
//        int res=0;
//        while (max != 0){
//            res++;
//            max/=10;
//        }
//        return res;
//    }
//
//    //arr[begin..end]排序、digit:最大的数字有多少个十进制位
//    public static void radixSort(int[] arr,int L,int R,int digit){
//        final int radix=10;
//        int j=0;
//        //有多少个数准备多少个辅助空间
//        int[] bucket=new int[R - L + 1];
//        for (int d = 1; d <= digit ; d++) {//有多少位就进出多少次
//            int []count=new int[radix];// count[0..9]
//            for (int i = L; i <= R ; i++) {
//                j=getDight(arr[i],d);
//                count[j]++;
//            }
//            for (int i = 1; i <radix ; i++) {
//                count[i]=count[i] + count[i - 1];
//            }
//            for (int i = R; i >= L  ; i--) {
//                j=getDight(arr[i],d);
//                bucket[count[j] - 1] =arr[i];
//                count[j]--;
//            }
//            for (int i = L; i <= R ; i++,j++) {
//                arr[i]=bucket[j];
//            }
//        }
//    }
//    public static int getDight(int x,int d){
//        return (int) (((x / Math.pow(10,d-1))) % 10))
//    }
//}
/**
 *           时间      空间     稳定
 *    选择   O(N2)     O(1)      不
 *    冒泡   O(N2)     O(1)      稳定
 *    插入   O(N2)     O(1)      稳定
 *    归并   O(N*logN) O(N)      稳定    追求稳定
 *    堆     O(N*logN) O(1)      不     追求额外空间
 *    快排   O(N*logN) O(logN)   不     不追求稳定性，只追求指标常数时间最优
 *    桶     O(N)      O(N)      稳定
 */
