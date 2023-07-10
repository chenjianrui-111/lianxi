package com.xiaochao.笔试;

/**
 * 要解决这个问题，可以使用二叉树的思路。在这个问题中，
 * 每一次对折相当于给二叉树添加一层。在每次对折过程中，左子树的折痕方向为凹，
 * 右子树的折痕方向为凸。因此，我们可以通过递归遍历二叉树的方式，
 * 打印从上到下的折痕方向。
 */
public class demo5 {
    public static void main(String[] args) {
        int n = 3; // 对折次数
        //打印凹痕方向
        printFolds(n);
    }

    private static void printFolds(int n) {
        printFoldsRecursive(n, true);
    }

    private static void printFoldsRecursive(int n, boolean down) {
        if (n == 0) {
            return;
        }

        // 先遍历左子树（凹）
        printFoldsRecursive(n - 1, true);

        // 打印当前节点折痕方向
        System.out.println(down ? "凹" : "凸");

        // 再遍历右子树（凸）
        printFoldsRecursive(n - 1, false);
    }
}
