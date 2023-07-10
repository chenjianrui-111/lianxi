package com.daimasuixianglu.huisu.zuhewenti;

import java.util.ArrayList;
import java.util.List;

/**
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 */
public class 复原IP地址 {
    List<String> res=new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        if (s.length()>12) return res;//剪枝
        backTrack(s,0,0);
        return res;
    }
    // startIndex: 搜索的起始位置， pointNum:添加逗点的数量
    public void backTrack(String s,int startIndex,int pointNum){
        if (pointNum==3) {
            //逗点数量为3时，分隔结束
            // 判断第四段⼦字符串是否合法，如果合法就放进result中
            if (isValid(s,startIndex,s.length()-1)){
                res.add(s);
            }
            return;
        }
        for (int i = startIndex; i <s.length() ; i++) {
            if (isValid(s,startIndex,i)){
                s=s.substring(0,i+1)+"."+s.substring(i+1);//在str的后⾯插⼊⼀个逗点
                pointNum++;
                backTrack(s,i+2,pointNum);// 插⼊逗点之后下⼀个⼦串的起始位置为i+2
                pointNum--;// 回溯
                s=s.substring(0,i+1)+s.substring(i+2);// 回溯删掉逗点
            }else {
                break;
            }
        }
    }
    // 判断字符串s在左闭⼜闭区间[start, end]所组成的数字是否合法
    public boolean isValid(String s,int start,int end){
        if (start>end){
            return false;
        }
        if (s.charAt(start)=='0'&& start!=end){// 0开头的数字不合法
            return false;
        }
        int num=0;
        for (int i = start; i < end; i++) {
            if (s.charAt(i)>'9'|| s.charAt(i)<'0'){// 遇到⾮数字字符不合法
                return false;
            }
            num=num*10+(s.charAt(i)-'0');
            if (num>255){ // 如果⼤于255了不合法
                return false;
            }
        }
        return true;
    }
}
