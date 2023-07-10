package com.daimasuixianglu.huisu.pailiewenti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 */
public class 全排列二 {
    List<List<Integer>> res=new ArrayList<>();
    LinkedList<Integer> path=new LinkedList<>();
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {
        used=new boolean[nums.length];
        Arrays.fill(used,false);
        Arrays.sort(nums);
        if (nums.length==0) {
            res.add(new ArrayList<>(path));
            return res;
        }
        backTracking(nums);
        return res;
    }
    public void backTracking(int[] nums){
        if (path.size()==nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i <nums.length ; i++) {
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            //如果同⼀树⽀nums[i]没使⽤过开始处理
            if (used[i]==false) {
                used[i]=true;//标记同⼀树⽀nums[i]使⽤过，防止同一树支重复使用
                path.add(nums[i]);
                backTracking(nums);
                path.removeLast();//回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                used[i]=false;//回溯
            }
        }
    }
}
