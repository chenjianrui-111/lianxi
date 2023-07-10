package com.cjr.jichu;


import static jdk.nashorn.internal.objects.Global.print;

public class MergeSortTest {

    public static void main(String[] args) {
        int data[]=new int[]{5,3,6,2,1,9,4,8,7};
        print(data);
        mergeSort(data);
        System.out.println("排序后的数组：");
        print(data);
    }
    public static void mergeSort(int[] data){
        sort(data,0,data.length-1);
    }
    public static void sort(int[] data,int left,int right){
        if (left>=right) return;
        // 找出中间索引
        int center = (left + right) / 2;
        // 对左边数组进行递归
        sort(data,left,center);
        // 对右边数组进行递归
        sort(data, center + 1, right);
        // 合并
        merge(data, left, center, right);
        print(data);
    }

    /**
     *  将两个数组进行归并，归并前面 2 个数组已有序，归并后依然有序
     */

    public static void merge(int[] data, int left, int center, int right) {
        //临时数组
        int[] tmpArr = new int[data.length];
        //右数组第一个元素索引
        int mid = center + 1;
        //thrid记录临时数组的索引
        int thrid = left;
        //缓存左数组第一个元素的索引
        int tmp = left;
        while (left <= center && mid <= right) {
            // 从两个数组中取出最小的放入临时数组
            if (data[left] <= data[mid]) {
                tmpArr[thrid++] = data[left++];
            } else {
                tmpArr[thrid++] = data[mid++];
            }
        }
        // 剩余部分依次放入临时数组（实际上两个 while 只会执行其中一个）
        while (mid <= right) {
            tmpArr[thrid++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[thrid++] = data[left++];
        }
        // 将临时数组中的内容拷贝回原数组中
        // （原 left-right 范围的内容被复制回原数组）
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
    }
        public static void print(int[] data) {
            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i] + "\t");
            }
            System.out.println();
    }

}
