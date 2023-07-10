package com.cjr.paixu;

public class souSuoXuanZhuanPaiXuShuZu {
    public int search(int[] nums, int target) {
        int length=nums.length;
        if (length==0){
            return -1;
        }
        if (length==1){
            return nums[0]==target?0:-1;
        }
        int l=0,r=length-1;
        while (l<=r){
            int mid=l+(r-l)/2;
            if (nums[mid]==target){
                return mid;
            }
            if (nums[0]<=nums[mid]){
                if (nums[0]<=target &&target<nums[mid]){
                    r=mid-1;
                }else {
                    l=mid+1;
                }
            }else {
                if (nums[mid]<target &&target<nums[r]){
                    l=mid+1;
                }else {
                    r=mid-1;
                }
            }
        }
        return -1;
    }
}
