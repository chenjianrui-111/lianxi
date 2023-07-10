package com.leixing.二分;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 */
public class 搜索旋转排序数组 {
    public int search(int[] nums, int target) {
     int n=nums.length;
     int idx=0;
        for (int i = 0; i <n - 1 ; i++) {
            if (nums[i] > nums[i+1]){
                idx=i;
                break;
            }
        }
        int ans=find(nums,0,idx,target);
        if (ans != -1) return  ans;
        if (idx + 1 < n) ans=find(nums,idx+1,n-1,target);
        return ans;
    }
    int find(int [] nums,int l,int r,int target){
        while (l < r){
            int mid= l + r >> 1;
            if (nums[mid] >= target){
                r=mid;
            }else {
                l=mid+1;
            }
        }
        return nums[l] == target ? 1 : -1;
    }
}
/**
 *时间复杂度：先对数组进行一次遍历，找到 idx，复杂度为 O(n)，对 idx 前后进行二分查找，复杂度为 O(logn)。整体为 O(n)
 * 空间复杂度：O(1)
 */
