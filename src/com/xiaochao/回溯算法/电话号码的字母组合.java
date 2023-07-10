package com.xiaochao.回溯算法;

import java.util.LinkedList;
import java.util.List;

/**
 * 示例 1：输⼊：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
public class 电话号码的字母组合 {

    // 每个数字到字⺟的映射
    String[] mapping = new String[] {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv",
            "wxyz"
    };

    List<String> res = new LinkedList<>();
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()){
            return res;
        }
        // 从 digits[0] 开始进⾏回溯
        backtrack(digits,0,new StringBuilder());
        return res;
    }
    void backtrack(String digits,int index,StringBuilder sb){
        if (sb.length() == digits.length()){
            //到达回溯树底部
            res.add(sb.toString());
            return;
        }
        for (int i = index; i <digits.length() ; i++) {
            int digit = digits.charAt(i) - '0';
            for (char c :mapping[digit].toCharArray()){
                //做选择
                sb.append(c);
                //递归下一层回溯树
                backtrack(digits, i + 1, sb);
                //撤销选择
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
