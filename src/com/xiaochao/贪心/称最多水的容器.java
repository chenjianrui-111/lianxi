package com.xiaochao.贪心;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 */
//朴素解法
//我们可以直接枚举所有的情况：先枚举确定左边界，再往右枚举确定右边界。
//然后再记录枚举过程中的最大面积即可
public class 称最多水的容器 {
    public int maxArea(int[] height) {
        int n = height.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n ; j++) {
                int w = j  - i;
                int h  = Math.min(height[i],height[j]);
                ans = Math.max(w * h,ans);
            }
        }
        return ans;
    }
}
//• 时间复杂度：O(n2) • 空间复杂度：O(1)
/**
 *双指针 + 贪心
 * 先用两个指针 i 和 j 指向左右边界，然后考虑指针应该怎么移动。
 * 由于构成矩形的面积，取决于 i 和 j 之间的距离（记为 w ） 和 i 和 j 下标对应的高度的
 * 最小值（记为 h ）。
 * 首先无论是 i 指针往右移动还是 j 指针往左移动都会导致 w 变小，所以想要能够枚举到更
 * 大的面积，我们应该让 h 在指针移动后变大。
 * 不妨假设当前情况是 height[i] < heigth[j] （此时矩形的高度为 height[i] ），然后分情况讨论：
 * • 让 i 和 j 两者高度小的指针移动，即 i 往右移动：
 * ◦ 移动后，i 指针对应的高度变小，即
 * height[i] > height[i + 1] ： w 和 h 都变小了，面积一定变小
 * ◦ 移动后，i 指针对应的高度不变，即
 * height[i] = height[i + 1] ： w 变小， h 不变，面积一定变小
 * ◦ 移动后，i 指针对应的高度变大，即
 * height[i] < height[i + 1] ： w 变小， h 变大，面积可能会变大
 * • 让 i 和 j 两者高度大的指针移动，即 j 往左移动：
 * ◦ 移动后，j 指针对应的高度变小，即
 * height[j] > height[j - 1] ： w 变小， h 可能不变或者变小（当
 * height[j - 1] >= height[i] 时， h 不变；当
 * height[j - 1] < height[i] 时， h 变小），面积一定变小
 * ◦ 移动后，j 指针对应的高度不变，即
 * height[j] = height[j - 1] ： w 变小， h 不变，面积一定变小
 * ◦ 移动后，j 指针对应的高度变大，即
 * height[j] < height[j - 1] ： w 变小， h 不变，面积一定变小
 * 综上所述，我们只有将高度小的指针往内移动，才会枚举到更大的面积：
 */
class Solution1{
    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0 , j  = n - 1;
        int ans = 0;
        while (i < j){
            ans = Math.max(ans,(j - i) * Math.min(height[i],height[j]));
            if (height[i] < height[j]){
                i++;
            }else {
                j--;
            }
        }
        return ans;
    }
}
//• 时间复杂度：会对整个数组扫描一遍。复杂度为 O(n) • 空间复杂度：O(1)
