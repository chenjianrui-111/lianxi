package com.leixing.递归迭代.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯解法
 * 我们知道使用 Set 虽然是 O(1) 操作，但是只是均摊 O(1)。
 * 因此我们来考虑不使用 Set 的做法。
 * 我们使用 Set 的目的是为了去重，那什么时候会导致的重复呢？
 * 其实就是相同的元素，不同的决策方案对应同样的结果。
 * 举个🌰，[1,1,1] 的数据，只选择第一个和只选择第三个（不同的决策方案），结果是一样的。
 * 因此如果我们希望去重的话，不能单纯的利用「某个下标是否被选择」来进行决策，而是要找到某个数值的连续一段，根据该数值的选择次数类进行决策。
 * 还是那个🌰，[1,1,1] 的数据，我们可以需要找到数值为 1 的连续一段，然后决策选择 0 次、选择 1 次、选择 2 次 ... 从而确保不会出现重复
 * 也就是说，将决策方案从「某个下标是否被选择」修改为「相同的数值被选择的个数」。这样肯定不会出现重复，因为 [1,1,1] 不会因为只选择第一个和只选择第三个产生两个 [1] 的方案，只会因为 1 被选择一次，产生一个 [1] 的方案
 */
public class 子集二2 {
    public List<List<Integer>> subsetsWithDup(int [] nums){
        Arrays.sort(nums);
        List<List<Integer>> ans=new ArrayList<>();
        List<Integer> cur=new ArrayList<>();
        dfs(nums,0,cur,ans);
        return new ArrayList<>(ans);
    }

    /**
     * @param nums 原輸入數組
     * @param u 當前決策到原數組中的哪一位
     * @param cur 當前方案
     * @param ans 最終方案
     */
    void dfs(int[] nums, int u, List<Integer> cur, List<List<Integer>> ans) {
        int n=nums.length;
        if (n == u){
            ans.add(new ArrayList<>(cur));
            return;
        }

        //記錄當前位置是什麽值(令數值為t),並找出數值為t的連續一段
        int t=nums[u];
        int last=u;
        while (last < n && nums[last] == nums[u]) last++;

        //不選當前位置的元素，直接跳到last往下決策
        dfs(nums,last,cur,ans);

        //決策選擇不同個數的t的情況：選擇一個.2個.3個...k個
        for (int i = u;i < last;i++){
            cur.add(nums[i]);
            dfs(nums,last,cur,ans);
        }

        //回溯對數值t的選擇
        for(int i = u; i < last;i++){
            cur.remove(cur.size() - 1);
        }
    }
}
//时间复杂度：排序复杂度为 O(nlogn)，爆搜复杂度为 (2^n)每个方案通过深拷贝存入答案，复杂度为 O(n)。整体复杂度为 (n * 2^n)
//空间复杂度：总共有 2^n 个方案，每个方案最多占用 O(n) 空间，整体复杂度为 (n * 2^n)
