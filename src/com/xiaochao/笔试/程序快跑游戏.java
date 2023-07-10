package com.xiaochao.笔试;

import java.util.Scanner;

/**
 * 在一款“程序借快跑”的小游戏中，每一局，程序通的初始能量值为C，程序通快跑的路上会遇到“能量盒”和“随碍物”，游戏设计规则包括:
 * (1)如果程字清触碰到“能最意”则当俞能量值翻倍
 * (2)如果程序造触碰到“峰碍物”则玩家能量值减1。
 * (3)现在游戏需要控制:如果系统设置的所有“能量盒”和“碍物”，玩家都碰到的话，则该玩家仍然可以达到终点，且在终点时的能量值为 0
 * 注：
 * (1) 玩家能量值为正整数，且可无限存储能量，但如果路途中，玩家能力值已小于或等于0。则游戏结束.
 * (2)每局初始能量值C固定为3，"能量盒”总数量M(0<M<=20)，“障碍物”总数量N(O<N<=20)问:假设限定了每局游戏中的“能量盒”总数量M值和“障碍物”总数量N值，现在需要你计算出满足游戏设计规则的排列次序种数。
 * 如假设M=2，N=6。则: nmnnnmnn就是合理的次序之一。
 * 说明:
 * (1)输人格式:M、N分别为“能量”和“障碍物”总数量.
 * (2) 出格式:计算出游戏设计规则的排列次序种数。如: M=2，N=6，则输出结果:2
 */
 class 程序快跑游戏 {
    private static int count = 0;
    private static void backtrack(int M, int N, int energy) {
        if (energy <= 0) return;

        if (M == 0 && N == 0 && energy == 3) {
            count++;
            return;
        }
        if (M > 0) {
            // Process an energy box
            backtrack(M - 1, N, energy * 2);
        }
        if (N > 0) {
            // Process an obstacle
            backtrack(M, N - 1, energy - 1);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();

        backtrack(M, N, 3);
        System.out.println(count);
        scanner.close();
    }
}
