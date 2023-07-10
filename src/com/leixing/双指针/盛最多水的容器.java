package com.leixing.双指针;

/**
 * 双指针 + 贪心
 * 先用两个指针 i 和 j 指向左右边界，然后考虑指针应该怎么移动。
 *
 * 由于构成矩形的面积，取决于 i 和 j 之间的距离（记为 w） 和 i 和 j 下标对应的高度的最小值（记为 h）。
 *
 * 首先无论是 i 指针往右移动还是 j 指针往左移动都会导致 w 变小，所以想要能够枚举到更大的面积，我们应该让 h 在指针移动后变大。
 *
 * 不妨假设当前情况是 height[i] < heigth[j]（此时矩形的高度为 height[i]），然后分情况讨论：
 *
 * 让 i 和 j 两者高度小的指针移动，即 i 往右移动：
 *
 * 移动后，i 指针对应的高度变小，即 height[i] > height[i + 1]：w 和 h 都变小了，面积一定变小
 * 移动后，i 指针对应的高度不变，即 height[i] = height[i + 1]：w 变小，h 不变，面积一定变小
 * 移动后，i 指针对应的高度变大，即 height[i] < height[i + 1]：w 变小，h 变大，面积可能会变大
 * 让 i 和 j 两者高度大的指针移动，即 j 往左移动：
 *
 * 移动后，j 指针对应的高度变小，即 height[j] > height[j - 1]：w 变小，h 可能不变或者变小（当 height[j - 1] >= height[i] 时，h 不变；当 height[j - 1] < height[i] 时，h 变小），面积一定变小
 * 移动后，j 指针对应的高度不变，即 height[j] = height[j - 1]：w 变小，h 不变，面积一定变小
 * 移动后，j 指针对应的高度变大，即 height[j] < height[j - 1]：w 变小，h 不变，面积一定变小
 * 综上所述，我们只有将高度小的指针往内移动，才会枚举到更大的面积：
 *
 */
public class 盛最多水的容器 {

    public int maxArea(int[] height) {
        int n = height.length;
        int i = 0, j = n - 1;
        int ans = 0;
        while (i < j) {

         ans=Math.max(ans,(j - i)*Math.min(height[i],height[j]));
         if (height[i] < height[j]){
             i++;
         }else {
             j--;
         }
        }
        return ans;
    }
}
