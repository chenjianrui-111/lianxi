package com.xiaochao.笔试;

/**
 * 给定一个长度为n只由数字0到9组成的字符串s，找出s中有多少个子串满足:数字出现次数的最大值小于出现的不同数字的数量，其中n<=50.
 * 示例1输入"2023"
 * 输出
 * 满足条件的子串有:"20"
 * "02"
 * "23"
 * "023"
 * "2023"
 */
public class 数字出现次数的最大值小于出现的不同数字的数量 {
    public static int countValidSubstrings(String s) {
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = s.substring(i, j);
                if (isValid(substring)) {
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean isValid(String s) {
        int[] digitsCount = new int[10];

        for (char c : s.toCharArray()) {
            digitsCount[c - '0']++;
        }
        int maxCount = 0;
        int distinctDigits = 0;
        for (int count : digitsCount) {
            if (count > 0) {
                distinctDigits++;
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount < distinctDigits;
    }
    public static void main(String[] args) {
        String s = "2023";
        int i = countValidSubstrings(s);
        System.out.println(i);
    }
}
