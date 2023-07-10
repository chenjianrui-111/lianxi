package com.daimasuixianglu.shuzu;

/**
 * 场景一：将所有的奇数放在前面，偶数放在后面。
 * 思路：定义左右游标，left和right，当left和right没有相遇的时候，先从左边开始遍历，如果遇到的是偶数，
 * left++，继续寻找，直到找到一个奇数，同理，从右边开始找，找到一个偶数，此时，如果left和right没有相遇，那么交换两个位置的数字。
 */
public class 将数组的奇数放在前面偶数放在后面 {
    public static void main(String[] args) {
        int[] arr=new int[]{1,2,3,4,5,6};
        int[] evenOdd = evenOdd(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(evenOdd[i]);
        }
    }
    public static int[] evenOdd(int[] arr){
        int left = 0;
        int right =arr.length - 1;
        while (left < right){
            while (left < right && arr[left] % 2 != 0){
                left++;
            }
            while (left < right && arr[right] % 2 != 1){
                right--;
            }
            if (left < right){
                int temp=arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        return arr;
    }
}
/**
 *场景二：奇数放在前面，偶放在后面，并且保持相对位置不发生变化。
 * 该问题相比于上一个问题，多了一个条件：相对位置不发生变化，比如说，交换之前的数字是123456，那么交换之后的顺序就应该是135246
 * 思路：借助插入排序的思想，从左边往右边开始循环遍历数组，遇到奇数先记录下来，将奇数之前的所有数字往后移动，将奇数插入进去。
 * 举个例子，有数字423456，，此时遍历数组，遇到的第一个奇数是3，先记录到临时变量里面，然后将3之前的所有数字往后移动，最后将3放到第一个位置，
 * 其他数字同理。这样就可以保证所有的奇数在前面，偶数在后面并且奇数偶数的相对位置不发生改变。
 */
class Solution{
    public static void evenOdd2(int[] arr){
        int k = 0;
        int temp = 0;
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i] % 2 != 0){
                temp=arr[i];
                int j=i;
                while (j > k){
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[k++] = temp;
            }
        }
    }
}
