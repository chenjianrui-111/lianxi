package com.daimasuixianglu.huisu.zuhewenti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
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
 * void backtracking(参数) {
 *     if (终止条件) {
 *         存放结果;
 *         return;
 *     }
 *
 *     for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
 *         处理节点;
 *         backtracking(路径，选择列表); // 递归
 *         回溯，撤销处理结果
 *     }
 * }
 */
public class 组合 {
    LinkedList<Integer> path=new LinkedList<>();// 用来存放符合条件结果
    List<List<Integer>> res=new ArrayList<>();// 存放符合条件结果的集合
    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n,k,1);
        return  res;
    }
    public void combineHelper(int n,int k,int startIndex){
        //终止条件
        if (path.size()==k){
            res.add(new ArrayList<>(path));
            return;
        }
        //单层逻辑
        for (int i = startIndex; i <=n ; i++) {// 处理节点
            path.add(i);
            combineHelper(n,k,i+1);// 递归
            path.removeLast();//回溯
        }
    }
}

/**
 * 剪枝优化
 * 遍历的范围是可以剪枝优化的
 * 已经选择的元素个数：path.size();
 * 还需要的元素个数为: k - path.size();
 * 在集合n中至多要从该起始位置 : n - (k - path.size()) + 1，开始遍历
 * 为什么有个+1呢，因为包括起始位置，我们要是一个左闭的集合。
 * 举个例子，n = 4，k = 3， 目前已经选取的元素为0（path.size为0），n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2。
 * 从2开始搜索都是合理的，可以是组合[2, 3, 4]。
 */
class Solution12{
    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        combineHelper(n, k, 1);
        return result;
    }
    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    private void combineHelper(int n, int k, int startIndex){
        //终止条件
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++){
            path.add(i);
            combineHelper(n, k, i + 1);
            path.removeLast();
        }
    }
}
