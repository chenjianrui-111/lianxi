package com.cjr.demo;

/**
 * 有两个有序的整数数组，都是升序，合并整数数组保持原来顺序
 */
public class shuzu {
    public void merage(){
        int nums[]=new int[]{1,3,5,7,9};
        int nums1[]=new int[]{2,4,6,8};
        int nums2[]=new int[]{};
        int j=0;
        int k=0;
        for (int i=0;i<nums.length;i++){
            if (j<nums.length &&k<nums1.length){
                if (nums[j]<=nums1[k]){
                    nums2[i]=nums[j];
                    j++;
                }else if (nums[j]>nums1[k]){
                    nums2[i]=nums1[k];
                    k++;
                }
            }
            else if (j>=nums1.length){
                nums2[i]=nums1[k];
                k++;
                j++;
            }else {
                nums2[i]=nums[j];
                j++;
                k++;
            }
        }
        for (int num:nums2){
            System.out.println(num+"");
        }
    }
}
