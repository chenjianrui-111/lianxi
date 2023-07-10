package com.xiaochao.贪心;

import java.util.Arrays;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出："210"
 * 对于 nums 中的任意两个值 a 和 b，我们无法直接从常规角度上确定其大小/先后关系。
 * 但我们可以根据「结果」来决定 a 和 b 的排序关系：
 * 如果拼接结果 ab 要比 ba好，那么我们会认为 a 应该放在 b 前面。
 * 另外，注意我们需要处理前导零（最多保留一位）。
 */
public class 最小数 {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ss = new String[n];
        for (int i = 0; i < n ; i++) {
            ss[i] ="" + nums[i];
        }
        Arrays.sort(ss,(a,b)->{
            String ab = a + b;
            String ba = b + a;
            return ba.compareTo(ab);
        });
        StringBuilder sb = new StringBuilder();
        for (String s : ss) sb.append(s);
        int k = 0;
        int len  =sb.length();
        //去除前置零
        while (k < len - 1 && sb.charAt(k) == '0') k++;
        return sb.substring(k);
    }
}
