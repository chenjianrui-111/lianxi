package com.daimasuixianglu.huisu.zuhewenti;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class 电话号码的字母组合 {
    LinkedList<String> path= new LinkedList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null ||digits.length()==0){
            return path;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //迭代处理
        backTracking(digits, numString, 0);
        return path;
    }
    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuilder
    StringBuilder temp = new StringBuilder();
    //比如digits如果为“23”，num为0，则str表示2对应的abc
    public void backTracking(String digits,String[] numString,int num){
        //遍历全部一次记录一次得到的字符串
        if (num==digits.length()){
            path.add(temp.toString());
            return;
        }
        //str表示当前num对应的字符串
        String str=numString[digits.charAt(num)-'0'];
        for (int i = 0; i <str.length() ; i++) {
            temp.append(str.charAt(i));
            //c
            backTracking(digits,numString,num+1);
            //剔除末尾的继续尝试
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
