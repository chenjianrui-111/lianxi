package com.daimasuixianglu.huisu.zuhewenti;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 */
public class 分割回文串 {
    List<List<String>> res=new ArrayList<>();
    LinkedList<String> path=new LinkedList<>();
    public List<List<String>> partition(String s) {
        backTracking(s,0);
        return res;
    }
    //递归函数参数还需要startIndex，因为切割过的地方，不能重复切割，和组合问题也是保持一致的。
    public void backTracking(String s,int startIndex){
        // 如果起始位置已经大于s的大小，说明已经找到了一组分割方案了
        if (startIndex>=s.length()){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = startIndex; i <s.length() ; i++) {
            //如果是回文子串，则记录
            if (isPalindrome(s,startIndex,i)){
                // 获取[startIndex,i]在s中的子串
                String str=s.substring(startIndex,i+1);
                path.addLast(str);
            }else {// 如果不是则直接跳过
                continue;
            }
            //起始位置后移，保证不重复
            //注意切割过的位置，不能重复切割，所以，backtracking(s, i + 1); 传入下一层的起始位置为i + 1。

            backTracking(s, i + 1);
            path.removeLast(); // 回溯过程，弹出本次已经填在的子串
        }
    }
    //判断是否是回文串
    private boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
