package com.daimasuixianglu.erchashu;

import java.util.ArrayList;

/**
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * 假定 BST 满足如下定义：
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 */
public class 二叉搜索树中的众数 {
    ArrayList<Integer> resList;
    int maxCount;// 最大频率
    int count;// 统计频率
    TreeNode pre;//上一个节点，因为是相邻节点比较

    public int[] findMode(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        findMode1(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    public void findMode1(TreeNode root) {
        if (root == null) {
            return;
        }
        findMode1(root.left);// 左

        int rootValue = root.val;
        // 计数
        if (pre == null || rootValue != pre.val) {//第一个节点或 与前一个节点数值不同
            count = 1;
        } else {// 与前一个节点数值相同
            count++;
        }
        // 更新结果以及maxCount
        if (count > maxCount) {// 如果计数大于最大值频率
            resList.clear();// 很关键的一步，不要忘记清空result，之前result里的元素都失效了
            resList.add(rootValue);
            maxCount = count;// 更新最大频率
        } else if (count == maxCount) {// 如果和最大值相同，放进result中
            resList.add(rootValue);
        }
        pre = root;// 更新上一个节点

        findMode1(root.right);// 右
    }
}
