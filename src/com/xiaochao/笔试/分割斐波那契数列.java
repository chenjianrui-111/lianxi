package com.xiaochao.笔试;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // 导入Scanner类来读取输入

class Main22 {

//    public static String isPossibleToSplit(int n, int k) {
//        List<Integer> fib = new ArrayList<>();
//        fib.add(2);
//        fib.add(1);
//        while (fib.get(fib.size() - 1) < n) {
//            fib.add(fib.get(fib.size() - 1) + fib.get(fib.size() - 2));
//        }
//
//
//        boolean[][] dp = new boolean[n + 1][k + 1];
//        dp[0][0] = true;
//
//        for (int i = 1; i < fib.size(); i++) {
//            for (int j = 1; j <= k; j++) {
//                for (int l = 1; l < i; l++) {
//                    if (fib.get(l) == i - l && dp[i - l][j - 1]) {
//                        dp[i][j] = true;
//                        break;
//                    }
//                }
//                if (dp[i][j]) break;
//            }
//        }
//
//        return dp[n][k] ? "Yes" : "No";
//    }

//    public static boolean isFibonacci(int n) {
//        if (n == 0 || n == 1) return true;
//        int a = 0, b = 1;
//        while (b < n) {
//            int tmp = a + b;
//            a = b;
//            b = tmp;
//        }
//        return b == n;
//    }
//
//    public static boolean isPossibleToSplit(int n, int k) {
//        List<Integer> fib = new ArrayList<>();
//        fib.add(1);
//        fib.add(1);
//        while (fib.get(fib.size()-1) < n) {
//            fib.add(fib.get(fib.size()-1) + fib.get(fib.size()-2));
//        }
//
//        int[] parts = new int[k];
//        Arrays.fill(parts, n/k);
//        for (int i = 0; i < n%k; i++) {
//            parts[i]++;
//        }
//
//        Arrays.sort(parts);
//
//        int cur = n;
//        for (int i = k-1; i >= 0; i--) {
//            cur -= parts[i];
//            if (!isFibonacci(cur)) {
//                return false;
//            }
//        }
//        return true;
//    }
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        int n = input.nextInt();
//        int k = input.nextInt();
//
//        boolean result = isPossibleToSplit(n, k);
//        System.out.println(result);
//    }
}
