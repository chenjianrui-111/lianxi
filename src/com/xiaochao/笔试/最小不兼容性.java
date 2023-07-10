package com.xiaochao.笔试;

/**
 * 给你一个整数数组 nums​和一个整数 k 。你需要将这个数组划分到 k 个相同大小的子集中，使得同一个子集里面没有两个相同的元素。
 * 一个子集的 不兼容性 是该子集里面最大值和最小值的差。
 * 请你返回将数组分成 k 个子集后，各子集 不兼容性 的 和 的 最小值 ，如果无法分成分成 k 个子集，返回 -1 。
 * 子集的定义是数组中一些数字的集合，对数字顺序没有要求。
 * 示例 1：
 * 输入：nums = [1,2,1,4], k = 2
 * 输出：4
 * 解释：最优的分配是 [1,2] 和 [1,4] 。
 * 不兼容性和为 (2-1) + (4-1) = 4 。
 * 注意到 [1,1] 和 [2,4] 可以得到更小的和，但是第一个集合有 2 个相同的元素，所以不可行。
 * 示例 2：
 * 输入：nums = [6,3,8,1,3,1,2,2], k = 4
 * 输出：6
 * 解释：最优的子集分配为 [1,2]，[2,3]，[6,8] 和 [1,3] 。
 * 不兼容性和为 (2-1) + (3-2) + (8-6) + (3-1) = 6 。
 * 示例 3：
 * 输入：nums = [5,3,3,6,3,3], k = 3
 * 输出：-1
 * 解释：没办法将这些数字分配到 3 个子集且满足每个子集里没有相同数字。
 * 提示：
 * 1 <= k <= nums.length <= 16
 * nums.length 能被 k 整除。
 * 1 <= nums[i] <= nums.length
 */

        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.HashSet;
        import java.util.Set;

/**
 * 1.预处理每组元素 保证 每组元素不会出现相同的元素
 * 2. dfs处理全部分组
 * 3. 处理过程中 保证分组之前没有重复使用数字
 * 4. dfs 0 表示可用 ，1 表示不可用 当全状态都是 1 就是已经找到一组解
 * 5. 最后返回 dp[0]
 */
public class 最小不兼容性 {
    public int minimumIncompatibility(int[] nums, int k) {
        //分组保证每组不包含重复元素
        int groupSize = nums.length/k;
        Arrays.sort(nums);
        int length = nums.length;
        HashMap<Integer,Integer> counter = new HashMap<>();
        build(0,0,nums,counter,0,length + 1,groupSize,0);
        int[] dp = new int[(1 << nums.length)];
        Set<Integer> integerSet = counter.keySet();
        int[] ints = new int[integerSet.size()];
        int i = 0;
        for (Integer integer : integerSet) {
            ints[i++] = integer;
        }
        Arrays.fill(dp,-2);
        return dfs1(0,(1 << nums.length) - 1,ints,0,dp,counter);
    }
    void build(int startindex,int pre,int[] nums,HashMap<Integer,Integer> counter,int max,int min,int level,int status){
        if (level == 0){
            counter.put(status,max - min);
            return;
        }
        for (int i = startindex; i < nums.length; i++) {
            if (nums[i] == pre) continue;
            build(i+1,nums[i],nums,counter,Math.max(max,nums[i]),Math.min(min,nums[i]),level - 1,status |(1 << i));
        }
    }
    int dfs1(int used,int all,int[] arr,int index,int[] dp,HashMap<Integer,Integer> counter){
        if (used == all) return 0;
        if (dp[used] != -2) return dp[used];
        int a =Integer.MAX_VALUE;
        for (int i = index; i < arr.length ; i++) {
            if ((arr[i] & used) == 0){
                int dfs = dfs1(arr[i] | used,all,arr,i+1,dp,counter);
                if (dfs != -1) a = Math.min(a,counter.get(arr[i]) + dfs);
            }
        }
        dp[used] = (a == Integer.MAX_VALUE ? -1 : a);
        return dp[used];
    }
}
 class Solution1 {
    Set<Integer>[] sets ;
    int[][] arr;
    int res = Integer.MAX_VALUE;
    public int minimumIncompatibility(int[] nums, int k) {
        // write code here
        int len = nums.length;
        Arrays.sort(nums);
        int t = len / k;
        int[] tong = new int[nums.length + 1];
        for (int num : nums) {
            tong[num]++;
        }
        for (int num : tong) {
            if (num > k) {
                return -1;
            }
        }
        sets = new HashSet[k];
        for (int i = 0; i < k; i++) {
            sets[i] = new HashSet<>();
        }
        arr = new int[k][2];
        for (int i = 0; i < k; i++) {
            arr[i][0] = Integer.MAX_VALUE;
            arr[i][1] = Integer.MIN_VALUE;
        }
        dfs(nums,0,k,t);
        return res;
    }

    private void dfs(int[] nums, int cur,int k,int t) {
        if(cur>=nums.length){
            int sum=0;
            for (int i = 0; i < k; i++) {
                sum+=arr[i][1]-arr[i][0];
            }
            res=Math.min(res,sum);
            return;
        }
        for (int i = 0; i < k; i++) {
            Set<Integer> set2 = sets[i];
            if (t==set2.size() || set2.contains(nums[cur])){
                continue;
            }
            set2.add(nums[cur]);
            int min = arr[i][0];
            int max = arr[i][1];
            arr[i][0]=Math.min(min,nums[cur]);
            arr[i][1]=Math.max(max,nums[cur]);
            dfs(nums,cur+1,k,t);
            set2.remove(nums[cur]);
            arr[i][0]=min;
            arr[i][1]=max;
            if(set2.size()==0){
                break;
            }
        }
    }
}
