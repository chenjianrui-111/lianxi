package com.xiaochao.笔试;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 数据范围：输入二叉树的节点数 0≤n≤1000，二叉树中每个节点的值 0≤val≤1000
 * 要求：空间复杂度O(1)（即在原树上操作），时间复杂度 O(n)
 * 注意:
 * 1.要求不能创建任何新的结点，只能调整树中结点指针的指向。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继
 * 2.返回链表中的第一个节点的指针
 * 3.函数返回的TreeNode，有左右指针，其实可以看成一个双向链表的数据结构
 * 4.你不用输出双向链表，程序会根据你的返回值自动打印输出。
 * 输入描述
 * 二叉树层次遍历
 * 输出描述
 * 从左往右结果 + 从右往左结果
 * 样例输入
 * 10
 * 6
 * 14
 * 4
 * 8
 * 12
 * 16
 * 样例输出
 * 4 6 8 10 12 14 16 16 14 12 10 8 6 4
 * 提示
 * stack
 * 中序遍历
 */
public class 二叉搜索树转换为双向链表 {
    // 双向链表的左边头结点和右边头节点
    Node leftHead = null;
    Node rightHead = null;
    /* Write Code Here */
    public Node  Convert(Node pRootOfTree) {
        // 递归调用叶子节点的左右节点返回null
        if (pRootOfTree == null)
            return null;
        // 第一次运行时，它会使最左边叶子节点为链表第一个节点
        Convert(pRootOfTree.left);
        if (rightHead == null) {
            leftHead = pRootOfTree;
            rightHead = pRootOfTree;
        } else { // 把根节点插入到双向链表右边，rightHead向后移动
            rightHead.right = pRootOfTree;
            pRootOfTree.left = rightHead;
            rightHead = pRootOfTree;
        }
        // 把右叶子节点也插入到双向链表（rightHead已确定，直接插入）
        Convert(pRootOfTree.right);
        // 返回左边头结点
        return leftHead;
    }
}
 class Solution02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Node res = null;
        List<Node> list = new ArrayList<>();

        while (in.hasNext()) {
            int item = in.nextInt();
            if (item == -1) {
                list.add(null);
            } else {
                list.add(new Node(item));
            }
        }
        int len = list.size();
        int i = 0;
        while (i <= (len - 2) / 2) {
            if (2 * i + 1 < len && list.get(i) != null) {
                list.get(i).left = list.get(2 * i + 1);
            }
            if (2 * i + 2 < len && list.get(i) != null) {
                list.get(i).right = list.get(2 * i + 2);
            }
            i++;
        }

        res = new  二叉搜索树转换为双向链表().Convert(list.get(0));
        if (res != null) {
            while (res.right != null && res.data != -1) {
                System.out.print(String.valueOf(res.data) + " ");
                res = res.right;
            }
            System.out.print(res.data + " ");
            while (res.left != null && res.data != -1) {
                System.out.print(String.valueOf(res.data) + " ");
                res = res.left;
            }
            System.out.print(res.data);
        }
        System.out.println();
    }
}
class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int data) {
        this.data = data;
    }

    public Node() {
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
