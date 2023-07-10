package com.xiaochao.笔试;

// 数组A中每间隔Y-1个的连续X个数的最小和
// 思路：滑动窗口
public class 数组A中间隔n的连续X个数的最小和 {
    public int solution(int[] A, int X, int Y) {
        // write your code in Java 8 (Java SE 8)
        int n = A.length;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < Y; i++) {
            int left = i, right = i;
            int sum = 0;
            int count = 0;
            while (right < n) {
                sum += A[right];
                right += Y;
                count++;
                while (count == X) {
                    res = Math.min(res, sum);
                    sum -= A[left];
                    left += Y;
                    count--;
                }
            }
        }
        return res;
    }
}
