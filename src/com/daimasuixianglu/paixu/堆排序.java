package com.daimasuixianglu.paixu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给你一个n代表有n个数字，然后你需要使用堆排序将这些数字从小到大排好。
 * 输入描述:
 * 第一行输入一个n，代表有n个数字
 * 第二行输入n个数
 * 输出描述:
 * 输出排序好后的n个数
 * 示例1
 * 输入
 * 4
 * 4 3 2 1
 * 输出
 * 1 2 3 4
 */
public class 堆排序 {
    public static void main(String[] args) throws IOException{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        String[] str = br.readLine().split(" ");
        int[] array = new int[count];
        for(int i = 0; i < count; i++){
            array[i] = Integer.parseInt(str[i]);
        }

        heapSort(array);

        for (int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
    }

    public static void heapSort(int[] array){
        if(array == null || array.length < 2){
            return;
        }
        //建堆
        for(int i = 0; i < array.length; i++){
            heapInsert(array,i);
        }

        //堆排序
        int heapSize = array.length;
        swap(array,0,  heapSize - 1);
        while (heapSize > 1){
            heapify(array,0,--heapSize);
            swap(array,0,heapSize - 1);
        }
    }

    public static void heapInsert(int[] array, int index){
        int fatherIndex = (index - 1)/2;
        while(array[index] > array[fatherIndex]){
            swap(array,index,fatherIndex);
            index = fatherIndex;
            fatherIndex = (index - 1) / 2;
        }
    }

    public static void heapify(int[] array,int index,int heapSize){
        int leftChild = 2*index + 1;
        while (leftChild < heapSize){
            int bigChild = ((leftChild + 1 < heapSize) && (array[leftChild] < array[leftChild + 1])) ? leftChild + 1 : leftChild;
            if (array[index] < array[bigChild]){
                swap(array,index,bigChild);
                index = bigChild;
                leftChild = 2*index + 1;
            }else{
                break;
            }
        }
        return;
    }

    public static void swap(int[] array,int index_1,int index_2){
        int temp = array[index_1];
        array[index_1] = array[index_2];
        array[index_2] = temp;
    }
}
