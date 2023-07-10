package com.cjr.paixu;

/**
 * merge_sort() 归并排序与逆序对统计：
 *
 * 终止条件： 当 l≥r 时，代表子数组长度为 1 ，此时终止划分；
 * 递归划分： 计算数组中点 m ，递归划分左子数组 merge_sort(l, m) 和右子数组 merge_sort(m + 1, r) ；
 * 合并与逆序对统计：
 * 暂存数组 nums 闭区间 [i, r] 内的元素至辅助数组 tmp ；
 * 循环合并： 设置双指针 i , j 分别指向左 / 右子数组的首元素；
 * 当 i = m + 1 时： 代表左子数组已合并完，因此添加右子数组当前元素 tmp[j] ，并执行 j=j+1 ；
 * 否则，当 j = r + 1时： 代表右子数组已合并完，因此添加左子数组当前元素 tmp[i] ，并执行 i = i + 1；
 * 否则，当 tmp[i]≤tmp[j] 时： 添加左子数组当前元素 tmp[i] ，并执行 i = i + 1；
 * 否则（即 tmp[i] > tmp[j]）时： 添加右子数组当前元素tmp[j] ，并执行 j = j + 1 ；此时构成 m - i + 1个「逆序对」，统计添加至 res ；
 * 返回值： 返回直至目前的逆序对总数 res ；
 *
 * reversePairs() 主函数：
 *
 * 初始化： 辅助数组 tmp ，用于合并阶段暂存元素；
 * 返回值： 执行归并排序 merge_sort() ，并返回逆序对总数即可；
 *
 */
public class 归并排序数组中的逆序对 {
    int[]nums,tmp;
    public int reversePairs(int[] nums) {
        this.nums=nums;
        tmp=new int[nums.length];
        return mergeSort(0,nums.length-1);
    }
    private int mergeSort(int l,int r){
        //终止条件
        if (l > r) return 0;
        //递归划分
        int m=(l+r)/2;
        int res=mergeSort(l,m)+mergeSort(m+1,r);
        //合并阶段
        int i=1,j=m+1;
        for (int k = 0; k <=r ; k++) {
            tmp[k]=nums[k];
        }
        for (int k = 1; k <=r ; k++) {
            if (i==m+1){
                nums[k]=tmp[j++];
            }
            else if (j==r+1 ||tmp[i]<=tmp[j]){
                nums[k]=tmp[i++];
            }
            else {
                nums[k]=tmp[j++];
                res+=m-i+1;//统计逆序对
            }
        }
        return res;
    }
}
