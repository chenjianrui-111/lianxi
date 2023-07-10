package com.daimasuixianglu.shuzu;

/**
 *双指针法
 * 数组其实是有序的， 只不过负数平方之后可能成为最大数了。
 * 那么数组平方的最大值就在数组的两端，不是最左边就是最右边，不可能是中间。
 * 此时可以考虑双指针法了，i指向起始位置，j指向终止位置。
 * 定义一个新数组result，和A数组一样的大小，让k指向result数组终止位置。
 * 如果A[i] * A[i] < A[j] * A[j] 那么result[k--] = A[j] * A[j]; 。
 * 如果A[i] * A[i] >= A[j] * A[j] 那么result[k--] = A[i] * A[i]; 。
 */
public class 有序数组的平方2 {
    public int[] sortedSquares(int[] nums) {
        int right=nums.length-1;
        int left=0;
        int [] result=new int[nums.length];
        int index=result.length-1;
        while (left<=right){
            if (nums[left]*nums[left] >nums[right]*nums[right]){
                result[index--]=nums[left]*nums[left];
                ++left;
            }else {
                result[index--]=nums[right]*nums[right];
                --right;
            }
        }
        return result;
    }
}
