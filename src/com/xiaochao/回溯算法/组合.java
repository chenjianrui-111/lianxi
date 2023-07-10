package com.xiaochao.回溯算法;

import java.util.LinkedList;
import java.util.List;

/**
 * 元素⽆重不可复选
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class 组合 {
    List<List<Integer>> res =new LinkedList<>();
    LinkedList<Integer> track =new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(1,n,k);
        return res;
    }
    void backtrack(int start,int n,int k){
        // base case
        if (k == track.size()){
            // 遍历到了第 k 层，收集当前节点的值
            res.add(new LinkedList<>(track));
            return;
        }
        //回溯算法标准框架
        for (int i = start; i <=n ; i++) {
            track.addLast(i);
            backtrack(i+1,n,k);
            track.removeLast();
        }
    }
}
