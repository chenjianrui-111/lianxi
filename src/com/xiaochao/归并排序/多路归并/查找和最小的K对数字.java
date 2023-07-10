package com.xiaochao.归并排序.多路归并;

import java.util.*;

/**
 * 给定两个以 升序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * 示例 1:
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 */
public class 查找和最小的K对数字 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of nums1: ");
        int n = scanner.nextInt();
        int[] nums1 = new int[n];
        System.out.println("Enter the elements of nums1:");
        for (int i = 0; i < n; i++) {
            nums1[i] = scanner.nextInt();
        }
        System.out.print("Enter the size of nums2: ");
        int m = scanner.nextInt();
        int[] nums2 = new int[m];
        System.out.println("Enter the elements of nums2:");
        for (int i = 0; i < m; i++) {
            nums2[i] = scanner.nextInt();
        }
        System.out.print("Enter the value of k: ");
        int k = scanner.nextInt();

        List<List<Integer>> res = kSmallestPairs(nums1, nums2, k);
        System.out.println("The k smallest pairs are:");
        for (List<Integer> pair : res) {
            System.out.println(pair);
        }
    }
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        for (int i = 0; i < nums1.length && i < k; i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int num1 = curr[0], num2 = curr[1], idx = curr[2];
            res.add(Arrays.asList(num1, num2));
            if (idx == nums2.length - 1) {
                continue;
            }
            minHeap.offer(new int[]{num1, nums2[idx + 1], idx + 1});
        }
        return res;
    }
}
