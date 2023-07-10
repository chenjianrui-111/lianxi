package com.demo;
import java.util.Scanner;

public class 加汉宁窗 {
    static double PI =  3.1415927;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        if (sc.hasNextLine()) {
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
        }
        int[] res = new int[n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = (int) ((nums[i] * 0.5 * (1 - Math
                    .cos(2 * PI * i / n))));
            if (res[i] != 0 && res[i] > 0) {
                ans[i] = (int) (res[i] + 0.5);
            } else if (res[i] != 0 && res[i] < 0) {
                ans[i] = (int) (res[i] - 0.5);
            } else {
                ans[i] = res[i];
            }
            for (int j = 0; j < ans.length; j++) {
                System.out.println(ans[j]);
            }
        }
    }
}
