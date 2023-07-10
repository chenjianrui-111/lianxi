package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 小美的实验结果
 * 时间限制： 3000MS
 * 内存限制： 589824KB
 * 题目描述：
 *         小美在做一个实验，这个实验会在纸带上打印出若干个数字，已知该实验所呈现出的正确结果应该是存在某一个分割处k，在k之前打印出的数字都是小于0的，而在k之后的数字应该都是大于0的，那么在k之前如果某一个数据大于等于0，那么我们认为这个数据是异常的，同理，在k之后如果某一个数据小于等于0，那么我们也认为这个数据是异常的。
 *
 *         现在给出小美打印的纸带，且k是未知的，那么请问在最乐观的情况下至少有多少个实验数据是异常的。(显然如果出现0，无论k为哪个时刻，这个0数据都是异常的)
 * 输入描述
 *     输入第一行包含一个正整数n，表示小美在纸带上打印的数字数量。(1<=n<=100000)
 *     输入第二行包含n个整数 ，即小美在纸带上打印的数字，中间用空格隔开。数字仅会为 -1，0，1中的一个。
 *
 * 输出描述
 * 输出仅包含一个整数，表示至少有多少个实验数据是异常的。
 */
public class 小美的实验结果 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] nums = new int[num];
        for (int i = 0; i < num; i++) {
            nums[i] = sc.nextInt();
        }
        int[] min = new int[num + 2]; //记录左侧有多少个>=0的数 min:{0,0,1,2,3,4,4}
        int[] max = new int[num + 2]; //记录右侧有多少个<=0的数 max:{0,2,1,0,0,0,0}

        for (int i = 0; i < num; i++) {
            if (nums[i] < 0) {
                min[i + 1] = min[i];
            } else {
                min[i + 1] = min[i] + 1;
            }
        }
        for (int i = num - 1; i >= 0; i--) {
            if (nums[i] <= 0) {
                max[i + 1] = max[i + 2] + 1;
            } else {
                max[i + 1] = max[i + 2];
            }
        }
        min[min.length - 1] = min[min.length - 2]; //处理边界条件
        max[0] = max[1];                        //处理边界
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < num + 1; i++) {
            res = Math.min(res, min[i] + max[i + 1]);
        }

        System.out.println(res);
    }
}
