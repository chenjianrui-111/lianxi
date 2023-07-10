package com.xiaochao.笔试;

import java.util.*;

public class 周年准备纪念品的数量 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        int ans = 0;
        for (long i = 1; i <= n; ++i) {
            if (isValid(i)) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static boolean isValid(long n) {
        String s = String.valueOf(n);
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '2' && i + 1 < s.length() && s.charAt(i + 1) == '5') {
                return true;
            }
        }
        return false;
    }
}
